package com.example.technpractiseandroid.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.interactors.ApiInteractor
import com.example.technpractiseandroid.model.ActivityFromApi
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileVm @Inject constructor(
    val mAuth: FirebaseAuth,
    val apiInteractor: ApiInteractor
    ):ViewModel() {

    var newActivity = MutableLiveData<ActivityFromApi>()

        fun logOut(){
            mAuth.signOut()
        }

    fun letsFun(){
        apiInteractor.getSomeActivity(newActivity)
        Log.d("find bug", newActivity.toString())
    }
}