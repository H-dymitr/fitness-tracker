package com.example.fitness

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fitness.databinding.RecordingActivityBinding
import com.example.fitness.ui.recording.RecordingFragment
import com.example.fitness.utils.MapPresenter
import com.example.fitness.utils.Ui
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

class RecordingActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: RecordingActivityBinding
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    private val presenter: MapPresenter by lazy { MapPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecordingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment: Fragment = RecordingFragment.newInstance("wartość1", "wartość2")
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        binding.stopRecording.setOnClickListener {
            if (binding.stopRecording.text == getString(R.string.start_label)) {
                startTracking()
                binding.stopRecording.setText(R.string.stop_label)
            } else {
                stopTracking()
                binding.stopRecording.setText(R.string.start_label)
            }
        }

        presenter.onViewCreated()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        presenter.ui.observe(this) { ui ->
            updateUi(ui)
        }

        presenter.onMapLoaded()
        map.uiSettings.isZoomControlsEnabled = true
    }

    private fun startTracking() {
        binding.speedVal.text = ""
        binding.distanceVal.text = ""
        binding.timer.base = SystemClock.elapsedRealtime()
        binding.timer.start()
        map.clear()

        presenter.startTracking()
    }

    private fun stopTracking() {
        presenter.stopTracking()
        binding.timer.stop()
    }

    @SuppressLint("MissingPermission")
    private fun updateUi(ui: Ui) {
        if (ui.currentLocation != null && ui.currentLocation != map.cameraPosition.target) {
            map.isMyLocationEnabled = true
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(ui.currentLocation, 14f))
        }
        binding.distanceVal.text = ui.formattedDistance
        binding.speedVal.text = ui.formattedPace
        drawRoute(ui.userPath)
    }

    private fun drawRoute(locations: List<LatLng>) {
        val polylineOptions = PolylineOptions()

        map.clear()

        val points = polylineOptions.points
        points.addAll(locations)

        map.addPolyline(polylineOptions)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}