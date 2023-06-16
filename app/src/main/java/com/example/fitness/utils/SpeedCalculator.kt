package com.example.fitness.utils

class SpeedCalculator {
    companion object {
        fun calculateAverageSpeed(distance: Float, timeInMillis: Long): Float {
            // Obliczanie średniej prędkości (distance / time) w odpowiednich jednostkach

            // Oblicz prędkość w metrach na sekundę
            val distanceInMeters = distance
            val timeInSeconds = timeInMillis / 1000f // przeliczenie czasu na sekundy
            val averageSpeedInKph = (distanceInMeters / 1000)

            return averageSpeedInKph
        }
    }
}