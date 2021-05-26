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
    private val compositeDisposable = CompositeDisposable()

    override fun getActivity(newActivity: MutableLiveData<ActivityFromApi>) {
        compositeDisposable.add(
            boredApi.getRandomActivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        newActivity.value?.apply {
                            activity = response.activity
                            accessibility = response.accessibility
                            type = response.type
                            participants = response.participants
                            price = response.price
                            key = response.key
                            link = response.link
                        }
                    }, { exception ->
                        Log.d("find bug", "get response error ${exception.message.toString()}")
                    }
                ))
    }
}