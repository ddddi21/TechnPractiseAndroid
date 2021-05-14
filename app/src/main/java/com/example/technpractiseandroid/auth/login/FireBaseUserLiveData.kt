package com.example.technpractiseandroid.auth.login

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseUserLiveData : LiveData<FirebaseUser?>() {
    val firebaseAuth = FirebaseAuth.getInstance()

     val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        value = firebaseAuth.currentUser
    }

    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }


    override fun onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}