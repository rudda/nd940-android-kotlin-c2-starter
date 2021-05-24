package com.udacity.asteroidradar.data

import android.provider.SyncStateContract
import android.util.Log
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.domain.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.await

class AppRepository (private val appDatabase: AppDatabase){
    val pictureOfDay = appDatabase.pictureOfDay.getPictureOfDay()
    val asteroids = appDatabase.asteroid.getAsteroid()


    suspend fun refreshPictureOfDay() {
        withContext(Dispatchers.IO) {
            var result = NasaApi.retrofitService.daylyImage(Constants.API_KEY).await()
            appDatabase.pictureOfDay.insertPictureOfDay(result.asDatabaseModel())
        }
    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            var result = JSONObject(NasaApi.retrofitService.feed( Constants.API_KEY).await())
            val asteroids = parseAsteroidsJsonResult(result)
            appDatabase.asteroid.insetAsteroid(asteroids)

        }
    }
}