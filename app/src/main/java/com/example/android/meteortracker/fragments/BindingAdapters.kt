package com.example.android.meteortracker.fragments

import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.meteortracker.bussinessdata.Meteor

@BindingAdapter("nameOfMeteor")
fun TextView.bindToDisplayMeteorName(meteor: Meteor){
    meteor.let {
        text = it.name
    }
}


@BindingAdapter("yearOfMeteor")
fun TextView.bindToDisplayMeteorYear(meteor: Meteor){
    val yearRawString = meteor.year
    val yearString = yearRawString.subSequence(0,10)
    text = if (yearString == "1111-01-01") "NOT FOUND" else yearString
}

@BindingAdapter("massOfMeteor")
fun TextView.bindToDisplayMeteorMass(meteor: Meteor){
    meteor.let {
        text = "${it.mass} grams"
    }
}

@BindingAdapter("fallStateOfMeteor")
fun TextView.bindToDisplayMeteorFallState(meteor: Meteor){
    meteor.let {
        text = it.fall
    }
}

@BindingAdapter("latOfMeteor")
fun TextView.bindToDisplayMeteorLat(meteor: Meteor){
    meteor.let {
        text = it.lat.toString()
    }
}

@BindingAdapter("lngOfMeteor")
fun TextView.bindToDisplayMeteorLng(meteor: Meteor){
    meteor.let {
        text = it.lng.toString()
    }
}

@BindingAdapter("navButtonText")
fun Button.bindToDisplayMeteorNav(meteor: Meteor){
    meteor.let {
        text = "Navigate to ${meteor.name}"
    }
}