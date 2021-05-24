package com.udacity.asteroidradar.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.PropertyKey

@Parcelize
@Entity(tableName = "asteroid")
data class AsteroidDatabase(
    @PrimaryKey @Json(name = "id")  val id: Long,
    @ColumnInfo(name = "codename") @Json(name = "codename")  val codename: String,
    @ColumnInfo(name = "close_approach_date") @Json(name = "close_approach_date") val closeApproachDate: String,
    @ColumnInfo(name = "absolute_magnitude") @Json(name = "absolute_magnitude")  val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimated_diameter") @Json(name = "estimated_diameter")  val estimatedDiameter: Double,
    @ColumnInfo(name = "relative_velocity") @Json(name = "relative_velocity")   val relativeVelocity: Double,
    @ColumnInfo(name = "distance_from_earth") @Json(name = "distance_from_earth") val distanceFromEarth: Double,
    @ColumnInfo(name = "is_potentially_hazardous") @Json(name = "is_potentially_hazardous")  val isPotentiallyHazardous: Boolean) : Parcelable