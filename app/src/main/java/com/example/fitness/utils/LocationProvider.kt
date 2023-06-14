import android.annotation.SuppressLint
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.LinkedList
import kotlin.coroutines.resume

@SuppressLint("MissingPermission")
class LocationProvider(private val activity: AppCompatActivity) {

    private val client by lazy { LocationServices.getFusedLocationProviderClient(activity) }

    private val locations = LinkedList<LatLng>()
    private var distance = 0

    private val _liveLocations = MutableLiveData<List<LatLng>>()
    private val _liveDistance = MutableLiveData<Int>()
    private val _liveLocation = MutableLiveData<LatLng>()

    val liveLocations: LiveData<List<LatLng>> = _liveLocations
    val liveDistance: LiveData<Int> = _liveDistance
    val liveLocation: LiveData<LatLng> = _liveLocation

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            val currentLocation = result.lastLocation
            val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)

            val lastLocation = locations.lastOrNull()

            if (lastLocation != null) {
                distance += SphericalUtil.computeDistanceBetween(lastLocation, latLng).toInt()
                _liveDistance.value = distance
            }

            locations.add(latLng)
            _liveLocations.value = locations
        }
    }

    fun getUserLocation() {
        CoroutineScope(Dispatchers.Main).launch {
            val location = getLastLocation()
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                locations.add(latLng)
                _liveLocation.value = latLng
            }
        }
    }

    private suspend fun getLastLocation(): android.location.Location? =
        suspendCancellableCoroutine { continuation ->
            client.lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener { exception ->
                continuation.cancel(exception)
            }
        }

    @SuppressLint("VisibleForTests")
    fun trackUser() {
        if (isTrackingUser()) return

        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun isTrackingUser(): Boolean {
        val task = client.locationAvailability
        return task.isSuccessful && task.result?.isLocationAvailable == true
    }

    fun stopTracking() {
        client.removeLocationUpdates(locationCallback)
        locations.clear()
        distance = 0
    }
}
