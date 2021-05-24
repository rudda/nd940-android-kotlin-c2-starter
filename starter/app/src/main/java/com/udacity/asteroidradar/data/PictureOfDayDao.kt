package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureOfDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictureOfDay( pictures: PictureOfDayDatabase)

    @Query("SELECT * FROM picture_of_day LIMIT 0,1")
    fun getPictureOfDay(): LiveData<PictureOfDayDatabase>

}