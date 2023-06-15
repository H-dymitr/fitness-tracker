package com.example.fitness.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Activity::class], version = 3)
@TypeConverters(Convert::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getActivityDao(): ActivityDAO

    private class DatabaseCallback : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            // Wykonaj odpowiednie działania podczas tworzenia bazy danych, jeśli to konieczne
        }
    }

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "activity_table"
                    )
                        .addCallback(DatabaseCallback()) // Dodaj Callback do budowniczego bazy danych
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE as AppDatabase
            }
        }
    }
}
