package com.example.technpractiseandroid.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.technpractiseandroid.auth.login.FirebaseUserLiveData
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
        currentUser.value = firebaseAuth.currentUser
    }


//    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//        value = firebaseAuth.currentUser
//    }

    fun setUsername() {
        mAuth.addAuthStateListener(authStateListener)
        //currentUser null если не дебагом ( тоесть не успевает) -> краш, дебагом успевает
            currentUser.value = mAuth.currentUser
            Log.d("find bug", "${mAuth.currentUser}")
            username.value = "Hello ${mAuth.currentUser.displayName}"


    }


//    val authenticationState = FirebaseUserLiveData().map { user ->
//        if (user != null) {
//            AuthenticationState.AUTHENTICATED
//        } else {
//            AuthenticationState.UNAUTHENTICATED
//        }
//    }
//    enum class AuthenticationState {
//        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
//    }

}