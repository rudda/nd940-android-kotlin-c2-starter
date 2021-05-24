package com.udacity.asteroidradar.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.material.circularreveal.CircularRevealHelper
import com.udacity.asteroidradar.domain.Asteroid

@Dao
interface AsteroidDao {

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insetAsteroid( asteroid : List<AsteroidDatabase>)

    @Query("SELECT * FROM asteroid order by close_approach_date desc")
    fun getAsteroid(): LiveData<List<AsteroidDatabase>>


}