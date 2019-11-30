package com.daya.katalogfilm.model.favorite

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class TvShowFavModel(
    @PrimaryKey
    val id: Int,
    val title:String,
    val description:String,
    val poster_path:String,
    val backdrop_path:String,
    val date:String,
    val rating: Double
) :Parcelable
