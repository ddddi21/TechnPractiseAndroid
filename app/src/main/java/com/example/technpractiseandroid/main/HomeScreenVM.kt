package com.example.technpractiseandroid.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeScreenVM @Inject constructor(
    val mAuth: FirebaseAuth
): ViewModel(){


    val username = MutableLiveData("")

        var currentUser = MutableLiveData<FirebaseUser>()

    val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        if(firebaseAuth.currentUser != null){
            currentUser.value = firebaseAuth.currentUser
            Log.d("find bug", "${firebaseAuth.currentUser}")
            username.value = "Hello ${firebaseAuth.currentUser!!.displayName}"
        }
    }



    fun setUsername() {
        mAuth.addAuthStateListener(authStateListener)
    }



}