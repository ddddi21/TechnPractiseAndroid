package com.example.technpractiseandroid.interactors

import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.model.ActivityFromApi
import com.example.technpractiseandroid.repository.interfaces.ApiRepository
import javax.inject.Inject

class ApiInteractor @Inject constructor(
    private var apiRepository: ApiRepository
    ) {
    fun getSomeActivity(newActivity: MutableLiveData<ActivityFromApi>){
        apiRepository.getActivity(newActivity)
    }

}