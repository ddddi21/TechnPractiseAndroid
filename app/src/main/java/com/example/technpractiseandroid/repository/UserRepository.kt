package com.example.technpractiseandroid.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.user.User
import com.google.firebase.auth.FirebaseUser
import com.example.technpractiseandroid.helpers.Result
import com.google.firebase.auth.FirebaseAuth

interface UserRepository {
    suspend fun registerUserFromAuthWithEmailAndPassword(email: String, password: String, mAuth:FirebaseAuth): Result<FirebaseUser?>
    suspend fun createUserInFirestore(user: User): Result<Void?>
    fun addUserInfo( mAuth:FirebaseAuth, username:MutableLiveData<String>)
}