package com.example.technpractiseandroid.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class ProfileVm @Inject constructor(
    val mAuth: FirebaseAuth
) : ViewModel() {

    fun logOut() {
        mAuth.signOut()
    }

}