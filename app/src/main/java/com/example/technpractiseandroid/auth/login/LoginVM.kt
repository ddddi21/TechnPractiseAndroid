package com.example.technpractiseandroid.auth.login

import android.app.Activity
import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.base.startApp
import com.example.technpractiseandroid.interactors.UserInteractor
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.squareup.okhttp.Dispatcher
import io.ashdavies.rx.rxtasks.toSingle
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject


class LoginVM @Inject constructor(
        private val mAuth: FirebaseAuth,
        var userInteractor: UserInteractor
):ViewModel(){
        val email = MutableLiveData("")
        val password = MutableLiveData("")
        val emailError = MutableLiveData("")
        val passwordError = MutableLiveData("")

        var loginErrorMessage = MutableLiveData<String>()
        var isHaveError = false
        var currentUserId: String ?= null

        var task: Task<AuthResult>?= null

        fun onLoginClick(){
                validForm()
        }



        fun validForm(): Boolean{
                clearErrors()
                isHaveError = false
                if (email.value.isNullOrEmpty()) {
                        emailError.value = "Enter email"
                        isHaveError = true
                }  else {
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

        fun clearErrors(){
                emailError.value = ""
                passwordError.value = ""
                loginErrorMessage.value = ""
        }

}