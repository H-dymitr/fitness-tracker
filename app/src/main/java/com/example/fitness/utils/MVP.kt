package com.example.fitness.utils


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.fitness.R
import com.google.android.gms.maps.model.LatLng

class MapPresenter(private val activity: AppCompatActivity) {

    val ui = MutableLiveData(Ui.EMPTY)

    private val locationProvider = LocationProvider(activity)
    val liveSpeed = MutableLiveData(0F)

    private val permissionsManager = PermissionManager(activity, locationProvider)

    fun onViewCreated() {
        locationProvider.liveLocations.observe(activity) { locations ->
            val current = ui.value
            ui.value = current?.copy(userPath = locations)
        }

        locationProvider.liveLocation.observe(activity) { currentLocation ->
            val current = ui.value
            ui.value = current?.copy(currentLocation = currentLocation)
        }

        locationProvider.liveDistance.observe(activity) { distance ->
            val current = ui.value
            val formattedDistance = activity.getString(R.string.distance_value, distance)
            ui.value = current?.copy(formattedDistance = formattedDistance)
        }
    }

    private fun calculateAndSetAverageSpeed(distance: Float, timeInMillis: Long) {
        val avgSpeed = SpeedCalculator.calculateAverageSpeed(distance, timeInMillis)
        liveSpeed.postValue(avgSpeed)
    }

    fun onMapLoaded() {
        permissionsManager.requestUserLocation()
    }

    fun startTracking() {
        locationProvider.trackUser()
        val currentUi = ui.value
        ui.value = currentUi?.copy(
            formattedPace = Ui.EMPTY.formattedPace,
            formattedDistance = Ui.EMPTY.formattedDistance
        )
    }

    fun stopTracking() {
        locationProvider.stopTracking()
        val distance = locationProvider.getTotalDistance()
        val timeInMillis = locationProvider.getTotalTimeInMillis()
        calculateAndSetAverageSpeed(distance.toFloat(), timeInMillis)
    }
}

data class Ui(
    val formattedPace: String,
    val formattedDistance: String,
    val currentLocation: LatLng?,
    val userPath: List<LatLng>
) {
    companion object {
        val EMPTY = Ui(
            formattedPace = "",
            formattedDistance = "",
            currentLocation = null,
            userPath = emptyList()
        )
    }
}
