package com.example.fitness.data

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import javax.inject.Inject

class Repository @Inject constructor(private val ActivityDAO: ActivityDAO) {

    suspend fun insertActivity(activity:Activity) = ActivityDAO.insertActivity(activity)

    suspend fun deleteActivity(activity: Activity) = ActivityDAO.deleteActivity(activity)

    fun getJoggingActivity(): LiveData<Activity> {
        return ActivityDAO.getJoggingActivity()
    }

    fun getWalkingActivity(): LiveData<Activity> {
        return ActivityDAO.getWalkingActivity()
    }

    fun getCyclingActivity(): LiveData<Activity> {
        return ActivityDAO.getCyclingActivity()
    }

    fun getJoggingMap(): LiveData<Bitmap> {
        return ActivityDAO.getJoggingMap()
    }

    fun getWalkingMap(): LiveData<Bitmap> {
        return ActivityDAO.getWalkingMap()
    }

    fun getCyclingMap(): LiveData<Bitmap> {
        return ActivityDAO.getCyclingMap()
    }

    fun getAvgSpeedForActivity(activityName: String): LiveData<Float> {
        return ActivityDAO.getAvgSpeedForActivity(activityName)
    }

    fun getDistanceInMetersForActivity(activityName: String): LiveData<Double> {
        return ActivityDAO.getDistanceInMetersForActivity(activityName)
    }

    fun getTimeInMillisForActivity(activityName: String): LiveData<Long> {
        return ActivityDAO.getTimeInMillisForActivity(activityName)
    }

}