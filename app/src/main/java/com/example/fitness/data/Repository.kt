package com.example.fitness.data

import javax.inject.Inject

class Repository @Inject constructor(private val ActivityDAO: ActivityDAO) {

    suspend fun insertActivity(activity:Activity) = ActivityDAO.insertActivity(activity)

    suspend fun deleteActivity(activity: Activity) = ActivityDAO.deleteActivity(activity)

    fun getActivitySortedByDate() = ActivityDAO.getActivitySortedByDate()

    fun getActivitySortedByDistance() = ActivityDAO.getActivitySortedByDistance()

    fun getActivitySortedByTimeInMillis() = ActivityDAO.getActivitySortedByTimeInMillis()

    fun getActivitySortedByAvgSpeed() = ActivityDAO.getActivitySortedByAvgSpeed()

    fun getTotalAvgSpeed() = ActivityDAO.getTotalAvgSpeed()

    fun getTotalDistance() = ActivityDAO.getTotalDistance()

    fun getTotalTimeInMillis() = ActivityDAO.getTotalTimeInMillis()

}