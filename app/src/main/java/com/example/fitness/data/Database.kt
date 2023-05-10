package com.example.fitness.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Activity::class],
    version = 1
)
@TypeConverters(Convert::class)
abstract class Database: RoomDatabase() {

    abstract fun getActivityDao():ActivityDAO

}