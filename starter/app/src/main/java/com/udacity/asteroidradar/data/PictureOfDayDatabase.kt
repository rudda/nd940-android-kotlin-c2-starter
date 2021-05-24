package com.udacity.asteroidradar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.android.parcel.IgnoredOnParcel

@Entity(tableName = "picture_of_day")
data class PictureOfDayDatabase(
    @PrimaryKey( autoGenerate = false) val  id : Long,
    @ColumnInfo(name="media_type") val mediaType: String,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="url") val url: String)
