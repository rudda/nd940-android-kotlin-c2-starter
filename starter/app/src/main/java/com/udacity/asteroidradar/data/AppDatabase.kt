package com.udacity.asteroidradar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Volatile
private lateinit  var INSTANCE: AppDatabase

@Database(entities = [PictureOfDayDatabase::class, AsteroidDatabase::class], version = 1, exportSchema = false)
abstract  class AppDatabase : RoomDatabase() {
    abstract val pictureOfDay : PictureOfDayDao
    abstract  val asteroid: AsteroidDao
}

fun getDatabase(context: Context) : AppDatabase {
    synchronized(AppDatabase::class.java) {

        if (!::INSTANCE.isInitialized ) {
            return  Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration()
                .build()

        }

    }
    return INSTANCE
}