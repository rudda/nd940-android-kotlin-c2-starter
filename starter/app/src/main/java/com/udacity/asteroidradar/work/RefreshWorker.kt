package com.udacity.asteroidradar.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.data.AppRepository
import com.udacity.asteroidradar.data.getDatabase
import retrofit2.HttpException

class RefreshWorker (appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshWorker"
    }
    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = AppRepository(database)
        return try {
            Log.i("App", "doWork")
            repository.refreshPictureOfDay()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

}