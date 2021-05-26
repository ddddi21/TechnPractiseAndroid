package com.example.technpractiseandroid.auth.login


import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.interactors.UserInteractor
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject


class LoginVM @Inject constructor(
    var userInteractor: UserInteractor
) : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val emailError = MutableLiveData("")
    val passwordError = MutableLiveData("")

    var loginErrorMessage = MutableLiveData<String>()
    var isHaveError = false

    var task: Task<AuthResult>? = null

    fun onLoginClick() {
        validForm()
    }


    fun validForm(): Boolean {
        clearErrors()
        isHaveError = false
        if (email.value.isNullOrEmpty()) {
            emailError.value = "Enter email"
            isHaveError = true
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                emailError.value = "invalid email form"
                isHaveError = true
            } else {
                if (password.value.isNullOrEmpty()) {
                    passwordError.value = "Enter password"
                    isHaveError = true
                } else {
                    if (password.value!!.length < 6) {
                        passwordError.value =
                            "Password length less than 6 symbols"
                        isHaveError = true
                    }
                }
            }
        }
        return isHaveError
    }

    fun clearErrors() {
        emailError.value = ""
        passwordError.value = ""
        loginErrorMessage.value = ""
    }

}