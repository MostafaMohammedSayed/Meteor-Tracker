package com.example.android.meteortracker.network

import android.location.Location
import android.util.Log
import com.example.android.meteortracker.bussinessdata.Meteor
import org.json.JSONArray
import org.json.JSONObject

fun parseJsonResponse(jsonResponse: String): ArrayList<Meteor>{

    val meteorsList = ArrayList<Meteor>()
    val jsonArray = JSONArray(jsonResponse)
    Log.i("NetworkUtils", jsonArray.length().toString())

    for (i in 0..jsonArray.length()-1){
        val meteorObject =  jsonArray.getJSONObject(i)
        val name = meteorObject.getString("name")
        val id = meteorObject.getInt("id")
        val nameType = meteorObject.getString("nametype")
        val mass = meteorObject.optInt("mass",5555)
        val fall = meteorObject.getString("fall")
        val year = meteorObject.optString("year", "1111-01-01T00:00:00.000")
        val lat = meteorObject.optDouble("reclat",0.0)
        val lng = meteorObject.optDouble("reclong",0.0)

        val meteor = Meteor(name,id,nameType,mass,fall,year,lat,lng)
        meteorsList.add(meteor)
    }

    Log.i("NetworkUtils",meteorsList.size.toString())
    return meteorsList
}