package com.example.android.meteortracker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.meteortracker.bussinessdata.Meteor
import com.example.android.meteortracker.database.MeteorDatabase
import com.example.android.meteortracker.network.MeteorApi
import com.example.android.meteortracker.network.parseJsonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class Repository (val database: MeteorDatabase){

    val meteors = database.meteorDao.getMeteors()

    val egyMeteors = database.meteorDao.getEgyMeteors()

    suspend fun fetch() {
        withContext(Dispatchers.IO) {
            val jsonResponse = MeteorApi.retrofitService.getMeteors().await()
            Log.i("Repository", jsonResponse)
            val meteorList: ArrayList<Meteor> = parseJsonResponse(jsonResponse)
            val meteorArray = meteorList.toTypedArray()
            database.meteorDao.insertAll(*meteorArray)
        }
    }
}