package com.example.technpractiseandroid.repository.impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.helpers.Result
import com.example.technpractiseandroid.helpers.await
import com.example.technpractiseandroid.repository.UserRepository
import com.example.technpractiseandroid.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import timber.log.Timber

class UserRepositoryImpl : UserRepository {
    override suspend fun registerUserFromAuthWithEmailAndPassword(
        email: String,
        password: String,
        mAuth:FirebaseAuth
    ): Result<FirebaseUser?> {
        try
        {
            return when(val resultDocumentSnapshot = mAuth?.createUserWithEmailAndPassword(email, password)?.await())
            {
                is Result.Success -> {
                    Log.i("find bug", "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data.user
                    val user = mAuth.currentUser
                    user?.sendEmailVerification()
                    Result.Success(firebaseUser)
                }
                is Result.Error -> {
                    Log.e("find bug", "${resultDocumentSnapshot.exception}")
                    Result.Error(resultDocumentSnapshot.exception)
                }
                is Result.Canceled ->  {
                    Log.e("find bug", "${resultDocumentSnapshot.exception}")
                    Result.Canceled(resultDocumentSnapshot.exception)
                }
            }
        }
        catch (exception: Exception)
        {
            return Result.Error(exception)
        }
    }


    override fun addUserInfo(mAuth:FirebaseAuth, username: MutableLiveData<String>){
        var currentUser = mAuth.currentUser
        val profileUpdates = userProfileChangeRequest {
            displayName = username.value.toString()
        }

        currentUser?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("User profile updated.")
                }
            }
    }
    override suspend fun createUserInFirestore(user: User): Result<Void?> {
        TODO("Not yet implemented")
    }
}