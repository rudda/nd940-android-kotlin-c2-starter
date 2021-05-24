package com.udacity.asteroidradar.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.udacity.asteroidradar.data.AsteroidDatabase
import com.udacity.asteroidradar.data.PictureOfDayDatabase
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

data class PictureOfDay (@Json(name = "media_type") val mediaType: String,
                         val title: String,
                         val url: String)

fun PictureOfDay.asDatabaseModel() : PictureOfDayDatabase {
    return PictureOfDayDatabase(0, this.mediaType, this.title, this.url)
}

@Parcelize
data class Asteroid(
                    @Json(name = "codename") val codename: String,
                    @Json(name = "close_approach_date") val closeApproachDate: String,
                    @Json(name = "absolute_magnitude") val absoluteMagnitude: Double,
                    @Json(name = "estimated_diameter") val estimatedDiameter: Double,
                    @Json(name = "relative_velocity") val relativeVelocity: Double,
                    @Json(name = "distance_from_earth") val distanceFromEarth: Double,
                    @Json(name = "is_potentially_hazardous") val isPotentiallyHazardous: Boolean) : Parcelable

fun List<Asteroid>.asDatabaseModel(): List<AsteroidDatabase> {
    return map {
        AsteroidDatabase(0, it.codename, it.closeApproachDate, it.absoluteMagnitude,
                                it.estimatedDiameter, it.relativeVelocity, it.distanceFromEarth,
                                it.isPotentiallyHazardous)
    }
}