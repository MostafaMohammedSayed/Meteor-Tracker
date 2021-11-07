package com.example.android.meteortracker.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.meteortracker.bussinessdata.Meteor

@Dao
interface MeteorDao{

    @Query("Select*From meteor order by id Asc")
    fun getMeteors(): LiveData<List<Meteor>>

    @Query("Select*From meteor Where lat >= 22.0 And Lat<= 32.0 And lng >=24.0 And lng <= 37.0")
    fun getEgyMeteors(): LiveData<List<Meteor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg meteors: Meteor)
}

@Database(entities = [Meteor::class],version = 1)
abstract class MeteorDatabase: RoomDatabase(){
    abstract val meteorDao: MeteorDao
}

private lateinit var INSTANCE: MeteorDatabase

fun getMeteorDatabase(context: Context): MeteorDatabase{
    synchronized(Meteor::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            MeteorDatabase::class.java,
            "meteors").build()
        }
    }
    return INSTANCE
}