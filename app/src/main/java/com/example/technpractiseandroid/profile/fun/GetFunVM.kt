package com.example.technpractiseandroid.profile.`fun`

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.helpers.FunHelper
import com.example.technpractiseandroid.interactors.ApiInteractor
import com.example.technpractiseandroid.model.ActivityFromApi
import javax.inject.Inject

class GetFunVM @Inject constructor(
    val apiInteractor: ApiInteractor
): ViewModel() {
    var activity = MutableLiveData<String>()
    var accessibility = MutableLiveData<String>()
    var type = MutableLiveData<String>()
    var participants = MutableLiveData<String>()
    var price = MutableLiveData<String>()
    var link = MutableLiveData<String>()

    var isHasLink = MutableLiveData<Boolean>(true)

    var newActivity = MutableLiveData<ActivityFromApi>()

    fun letsFun(){
        apiInteractor.getSomeActivity(newActivity)
        activity.value = newActivity.value?.activity
        accessibility.value = newActivity.value?.let { FunHelper.translateAccessibility(it.accessibility) }
        type.value = newActivity.value?.type
        participants.value = newActivity.value?.participants.toString()
        price.value = newActivity.value?.let { FunHelper.translatePrice(it.price) }
        link.value = newActivity.value?.link?:""
        isHasLink.value = !newActivity.value?.link.isNullOrEmpty()
        Log.d("find bug", newActivity.toString())
    }
}