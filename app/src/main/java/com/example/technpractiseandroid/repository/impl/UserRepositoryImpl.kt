package com.example.technpractiseandroid.repository.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.repository.interfaces.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume

class UserRepositoryImpl(
    private val mAuth: FirebaseAuth
) : UserRepository {

    @InternalCoroutinesApi
    override suspend fun registration(
        email: String, password: String,
        registrationErrorMessage: MutableLiveData<String>
    ): AuthResult =
        suspendCancellableCoroutine { continuation ->
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Timber.d("createUserWithEmail:success")
                    val user = mAuth.currentUser
                    user?.sendEmailVerification()
                    continuation.resume(it)
                }
                .addOnFailureListener {
                    Log.d("reg", it.toString())
                    registrationErrorMessage.value = it.message.toString()
                    continuation.tryResumeWithException(it)
                }
        }


    @InternalCoroutinesApi
    override suspend fun login(
        email: String, password: String,
        loginErrorMessage: MutableLiveData<String>
    ): Task<AuthResult> =
        suspendCancellableCoroutine { continuation ->
            var task = mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        Log.d("find bug", "login isSuccessful")
                        continuation.resume(task)
                    } else {
                        Log.d("find bug", task?.exception.toString())
                        loginErrorMessage.value =
                            task.exception?.message.toString()
                        continuation.tryResumeWithException(exception = task.exception!!)
                    }
                }
        }

    @InternalCoroutinesApi
    override suspend fun addUserInfo(username: MutableLiveData<String>): Void =
        suspendCancellableCoroutine { continuation ->
            val currentUser = mAuth.currentUser

            val profileUpdates = userProfileChangeRequest {
                displayName = username.value.toString()
            }

            currentUser?.updateProfile(profileUpdates)
                ?.addOnSuccessListener { task ->
                    Timber.d("User profile updated.")
                    continuation.resume(task)
                }
                ?.addOnFailureListener {
                    continuation.tryResumeWithException(it)
                }
        }
}