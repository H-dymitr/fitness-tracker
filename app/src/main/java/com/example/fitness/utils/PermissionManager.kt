package com.example.fitness.utils

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.utils.LocationProvider

class PermissionManager(
    activity: AppCompatActivity,
    private val locationProvider: LocationProvider
) {

    private val locationPermissionProvider = activity.registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            locationProvider.getUserLocation()
        }
    }
    fun requestUserLocation() {
        locationPermissionProvider.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

}