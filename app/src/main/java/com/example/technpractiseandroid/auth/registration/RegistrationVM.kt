package com.example.technpractiseandroid.auth.registration

import android.app.Activity
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.di.modules.FirebaseModule_ProvideFirebaseCurrentUserFactory
import com.example.technpractiseandroid.repository.UserRepository
import com.example.technpractiseandroid.repository.impl.UserRepositoryImpl
import com.example.technpractiseandroid.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.coroutineContext


class RegistrationVM @Inject constructor(
private val mAuth: FirebaseAuth
): ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val username = MutableLiveData("")
    var emailError = MutableLiveData("")
    var usernameError= MutableLiveData("")
    var passwordError= MutableLiveData("")
    var isHaveError = false
    var registrationErrorMessage = ""
    var isHaveEnterError = true
    val userRepository: UserRepository = UserRepositoryImpl()


    var settingUsername = ""
    var userProfile: User ?= null

    //TODO(fix empty request)
    //fix
    fun onRegistrationClick(){
            isHaveEnterError = true
            validForm()
        viewModelScope.launch {
            userRepository.registerUserFromAuthWithEmailAndPassword(
                email = email.value.toString(),
                password = password.value.toString(), mAuth = mAuth
            )
        }
//            mAuth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
//                .addOnSuccessListener{
//                        // Sign in success, update UI with the signed-in user's information
//                        Timber.d("createUserWithEmail:success")
//                        val user = mAuth.currentUser
//                        user?.sendEmailVerification()
//                        isHaveEnterError = false
//                        addUserInfo()
//                    }
//                .addOnFailureListener{
//                        //если регистрироваться с существующей почтой, то не заходит сюда ->
//                        // не могу поймать ошибку
//                        // If sign in fails, display a message to the user.
//                        Log.d("reg", it.toString())
//                        registrationErrorMessage = it.toString()
//                }
    }



    fun addUserInfo(){
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


    fun validForm(): Boolean{
        clearErrors()
        isHaveError = false
        if (email.value.isNullOrEmpty()) {
            emailError.value = "Enter email"
            isHaveError = true
        } else{//add unique check
        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            emailError.value = "invalid email form"
            isHaveError = true
        }
        }
        if (username.value.isNullOrEmpty()) {
            usernameError.value = "Enter username"
            isHaveError = true

        }
        if (password.value.isNullOrEmpty()) {
            passwordError.value = "Enter password"
            isHaveError = true
        } else{
            if(password.value!!.length < 6){
                passwordError.value= "Password length less than 6 symbols"
                isHaveError = true
            }
        }
        return isHaveError
    }

    fun clearErrors(){
        emailError.value = ""
        usernameError.value = ""
        passwordError.value = ""
    }
}