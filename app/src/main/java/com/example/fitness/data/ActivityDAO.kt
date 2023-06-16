package com.example.fitness.data

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: Activity)

    @Delete
    suspend fun deleteActivity(activity: Activity)

    @Query("SELECT * FROM activity_table WHERE activityName = 'Jogging' ORDER BY timestamp DESC LIMIT 1")
    fun getJoggingActivity(): LiveData<Activity>

    @Query("SELECT * FROM activity_table WHERE activityName = 'Walking' ORDER BY timestamp DESC LIMIT 1")
    fun getWalkingActivity(): LiveData<Activity>

    @Query("SELECT * FROM activity_table WHERE activityName = 'Cycling' ORDER BY timestamp DESC LIMIT 1")
    fun getCyclingActivity(): LiveData<Activity>

    @Query("SELECT avgSpeedInKMH FROM activity_table WHERE activityName = :activityName ORDER BY timestamp DESC LIMIT 1")
    fun getAvgSpeedForActivity(activityName: String): LiveData<Float>

    @Query("SELECT distanceInMeters FROM activity_table WHERE activityName = :activityName ORDER BY timestamp DESC LIMIT 1")
    fun getDistanceInMetersForActivity(activityName: String): LiveData<Double>

    @Query("SELECT image FROM activity_table WHERE activityName = 'Jogging' ORDER BY timestamp DESC LIMIT 1")
    fun getJoggingMap(): LiveData<Bitmap>

    @Query("SELECT image FROM activity_table WHERE activityName = 'Walking' ORDER BY timestamp DESC LIMIT 1")
    fun getWalkingMap(): LiveData<Bitmap>

    @Query("SELECT image FROM activity_table WHERE activityName = 'Cycling' ORDER BY timestamp DESC LIMIT 1")
    fun getCyclingMap(): LiveData<Bitmap>

    @Query("SELECT timeInMillis FROM activity_table WHERE activityName = :activityName ORDER BY timestamp DESC LIMIT 1")
    fun getTimeInMillisForActivity(activityName: String): LiveData<Long>
}
