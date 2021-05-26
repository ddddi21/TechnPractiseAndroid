package com.example.technpractiseandroid.repository.interfaces

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface UserRepository {
   suspend fun registration(email: String, password:String,
                            registrationErrorMessage:MutableLiveData<String>): AuthResult
   suspend fun login(email: String, password:String,
                     loginErrorMessage: MutableLiveData<String>): Task<AuthResult>
   suspend fun addUserInfo(username: MutableLiveData<String>): Void
}