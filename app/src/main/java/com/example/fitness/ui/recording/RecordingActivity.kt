package com.example.fitness.ui.recording

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.MainActivity
import com.example.fitness.R
import com.example.fitness.data.ActivityDAO
import com.example.fitness.data.AppDatabase
import com.example.fitness.databinding.FragmentRecordingBinding
import com.example.fitness.utils.MapPresenter
import com.example.fitness.utils.SpeedCalculator
import com.example.fitness.utils.Ui
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordingActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: FragmentRecordingBinding
    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    private val presenter: MapPresenter by lazy { MapPresenter(this) }
    private lateinit var getActivityDAO: ActivityDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRecordingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        val db = AppDatabase.getInstance(applicationContext)
        getActivityDAO = db.getActivityDao()

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
        val timestamp = System.currentTimeMillis()
//        val avgSpeed = 10F // Placeholder value, replace with actual calculation
        val distance = 1000 // Placeholder value, replace with actual calculation
        val time = SystemClock.elapsedRealtime() - binding.timer.base

        val avgSpeed = SpeedCalculator.calculateAverageSpeed(distance.toFloat(), time)

        val record = com.example.fitness.data.Activity(
            timestamp = timestamp,
            avgSpeedInKMH = avgSpeed,
            distanceInMeters = distance.toDouble(),
            timeInMillis = time
        )

        // Call insertActivity within a coroutine
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                getActivityDAO.insertActivity(record)
            }
        }

        presenter.stopTracking()
        binding.timer.stop()
    }

    @SuppressLint("MissingPermission")
    private fun updateUi(ui: Ui) {
        if (ui.currentLocation != null && ui.currentLocation != map.cameraPosition.target) {
            map.isMyLocationEnabled = true
            map.uiSettings.isZoomControlsEnabled = true
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(ui.currentLocation, 14f))
        }

        binding.distanceVal.text = ui.formattedDistance
        binding.speedVal.text = ui.formattedPace
        presenter.liveSpeed.observe(this) { speed ->
            binding.speedVal.text = speed.toString()
        }

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