package com.example.technpractiseandroid.repository.interfaces

import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.model.ActivityFromApi


interface ApiRepository {

    fun getActivity(newActivity: MutableLiveData<ActivityFromApi>)
}