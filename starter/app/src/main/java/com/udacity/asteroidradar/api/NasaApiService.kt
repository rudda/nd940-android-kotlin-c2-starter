package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("neo/rest/v1/feed")
    fun feed( @Query(value="api_key") apiKey: String): Call<String>


    @GET("planetary/apod")
    fun daylyImage(@Query(value="api_key") key: String ): Call<PictureOfDay>
}