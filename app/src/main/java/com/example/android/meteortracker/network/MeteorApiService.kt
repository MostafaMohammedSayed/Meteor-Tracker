package com.example.android.meteortracker.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

const val URL = "https://data.nasa.gov/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(URL)
    .build()

interface MeteorApiService{

    @GET("resource/gh4g-9sfh.json")
    fun getMeteors(): Call<String>
}

object MeteorApi{
    val retrofitService: MeteorApiService by lazy {
        retrofit.create(MeteorApiService::class.java)
    }
}

