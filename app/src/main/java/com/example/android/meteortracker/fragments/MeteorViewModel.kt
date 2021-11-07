package com.example.android.meteortracker.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.meteortracker.Repository
import com.example.android.meteortracker.bussinessdata.Meteor
import com.example.android.meteortracker.database.getMeteorDatabase
import kotlinx.coroutines.launch

class MeteorViewModel(application: Application): AndroidViewModel(application){

    private val database = getMeteorDatabase(application)
    private val repo = Repository(database)

    private lateinit var _meteors: LiveData<List<Meteor>>
    val meteors: LiveData<List<Meteor>>
        get() = _meteors

    private lateinit var _egyMeteors: LiveData<List<Meteor>>
    val egyMeteors: LiveData<List<Meteor>>
        get() = _egyMeteors

    init {
        viewModelScope.launch {
            try {
                repo.fetch()
            }catch (e: Exception){
                e.message?.let { Log.i("ViewModel", it) }
            }
        }
        try {
            _meteors = repo.meteors
            _egyMeteors = repo.egyMeteors
            Log.i("ViewModel", repo.meteors.value?.size.toString())
            Log.i("ViewModel", _meteors.value?.size.toString())
        }catch (e: Exception){
            println(e.message)
        }

    }

    private val _navigateToDetail = MutableLiveData<Meteor?>()
    val navigateToDetail: LiveData<Meteor?>
        get() = _navigateToDetail

    fun onMeteorClicked(meteor: Meteor) {
        _navigateToDetail.value = meteor
    }

    fun onNavigationCompleted(){
        _navigateToDetail.value = null
    }

    class Factory (val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MeteorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MeteorViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel") }
    }

}