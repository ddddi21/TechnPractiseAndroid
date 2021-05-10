package com.example.technpractiseandroid.auth.registration

import android.app.Activity
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.di.modules.FirebaseModule_ProvideFirebaseCurrentUserFactory
import com.example.technpractiseandroid.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import com.google.firebase.auth.ktx.userProfileChangeRequest
import timber.log.Timber


class RegistrationVM @Inject constructor(
private val mAuth: FirebaseAuth,
private val db: FirebaseFirestore
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

    var settingUsername = ""
    var userProfile: User ?= null

    fun onRegistrationClick(activity: Activity) {
        isHaveEnterError = true
        mAuth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener(activity) { task -> //fix
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d("createUserWithEmail:success")
                    val user = mAuth.currentUser
                    user.sendEmailVerification()
                    isHaveEnterError = false
                    addUserInfo()
                } else {
                    //если регистрироваться с существующей почтой, то не заходит сюда ->
                        // не могу поймать ошибку
                    // If sign in fails, display a message to the user.
                    Log.d( "reg", task.exception.toString())
                    registrationErrorMessage = task.exception.toString()
                }
            }
    }
    fun checkCorrectReg(){
        if(isHaveEnterError){
            registrationErrorMessage ="something wrong, try another email"
        }
    }

//    fun addUserInfo(){
//        currentUser.let {
//            // Name, email address, and profile photo Url
//            userProfile?.name = currentUser.displayName
//            userProfile?.email = currentUser.email
//            userProfile?.profilePicture = currentUser.photoUrl.toString()
//
//            // Check if user's email is verified
//            userProfile?.isEmailVerified = currentUser.isEmailVerified
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getToken() instead.
//            userProfile?.userUid= currentUser.uid
//        }
//    }

    fun addUserInfo(){
        var currentUser = mAuth.currentUser
        val profileUpdates = userProfileChangeRequest {
            displayName = username.value.toString()
        }

        currentUser.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("User profile updated.")
                }
            }
    }

//    fun profileUpdates(): UserProfileChangeRequest {
//         settingUsername = username.value.toString()
//    }



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