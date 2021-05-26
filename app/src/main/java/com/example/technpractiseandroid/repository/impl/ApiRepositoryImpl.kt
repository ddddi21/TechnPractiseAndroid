package com.example.technpractiseandroid.repository.impl

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.api.BoredApi
import com.example.technpractiseandroid.api.response.BoredResponse
import com.example.technpractiseandroid.model.ActivityFromApi
import com.example.technpractiseandroid.repository.interfaces.ApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ApiRepositoryImpl(
    private var boredApi: BoredApi
): ApiRepository {

    override fun getActivity(newActivity: MutableLiveData<ActivityFromApi>) {
            boredApi.getRandomActivity()
                .subscribeOn(Schedulers.io())
                .blockingGet()
                .also { response ->
                    var newNewActivityFromApi = ActivityFromApi("",0.0,
                    "", 0, 0.0, "", "")
                    newNewActivityFromApi.apply{
                        activity = response.activity
                        accessibility = response.accessibility
                        type = response.type
                        participants = response.participants
                        price = response.price
                        key = response.key
                        link = response.link
                    }
                    newActivity.value = newNewActivityFromApi
                    Log.d("find bug", newNewActivityFromApi.toString())
                }
    }
}