package com.example.android.meteortracker.bussinessdata

import android.location.Location
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Meteor(@PrimaryKey val name: String,
                  val id: Int,
                  val nameType: String,
                  val mass: Int,
                  val fall: String,
                  val year: String,
                  val lat: Double,
                  val lng: Double): Parcelable{}

