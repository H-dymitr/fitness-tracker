package com.example.fitness.data


import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_table")
data class Activity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var activityName:String = "",
    var image:Bitmap? = null,
    var timestamp:Long = 0L,
    var avgSpeedInKMH:Float = 0f,
    var distanceInMeters:Double,
    var timeInMillis:Long = 0L
)