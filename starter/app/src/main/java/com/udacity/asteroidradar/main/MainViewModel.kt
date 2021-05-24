package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.data.AppDatabase
import com.udacity.asteroidradar.data.AppRepository
import com.udacity.asteroidradar.data.PictureOfDayDatabase
import com.udacity.asteroidradar.data.getDatabase
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var  _response = MutableLiveData<PictureOfDay>()
    public val response : LiveData<PictureOfDay>
        get()  = response

    private val database = getDatabase(application)
    private val repository = AppRepository(database)
    val picture = repository.pictureOfDay
    val asteroids = repository.asteroids

    init {
        viewModelScope.launch {
            repository.refreshPictureOfDay()
            repository.refreshAsteroids()
        }
    }



}