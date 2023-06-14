package com.example.fitness.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActivityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActivity(activity: Activity)

    @Delete
    fun deleteActivity(activity: Activity)

    @Query("SELECT * FROM activity_table ORDER BY timestamp DESC")
    fun getActivitySortedByDate(): LiveData<List<Activity>>

    @Query("SELECT * FROM activity_table ORDER BY timeInMillis DESC")
    fun getActivitySortedByTimeInMillis():LiveData<List<Activity>>

    @Query("SELECT * FROM activity_table ORDER BY avgSpeedInKMH DESC")
    fun getActivitySortedByAvgSpeed():LiveData<List<Activity>>

    @Query("SELECT * FROM activity_table ORDER BY distanceInMeters DESC")
    fun getActivitySortedByDistance():LiveData<List<Activity>>

    @Query("SELECT SUM(timeInMillis) FROM activity_table")
    fun getTotalTimeInMillis():LiveData<Long>

    @Query("SELECT SUM(distanceInMeters) FROM activity_table")
    fun getTotalDistance():LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM activity_table")
    fun getTotalAvgSpeed():LiveData<Float>
}