package com.example.fitness.data


import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_table")
data class Activity(
    var image:Bitmap? = null,
    var timestamp:Long = 0L,
    var avgSpeedInKMH:Float = 0f,
    var distanceInMeters:Int = 0,
    var timeInMillis:Long = 0L
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}