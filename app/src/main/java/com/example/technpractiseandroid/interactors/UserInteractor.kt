package com.example.technpractiseandroid.interactors

import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.repository.interfaces.UserRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class UserInteractor @Inject constructor(
    private var userRepository: UserRepository,
    @Named("IO")private val context: CoroutineContext
) {

    suspend fun createUser(email: String, password:String,
                           registrationErrorMessage: MutableLiveData<String>){
        withContext(context){
            userRepository.registration(email, password, registrationErrorMessage)
        }
    }

    suspend fun addUserInfo(username: MutableLiveData<String>){
        withContext(context){
            userRepository.addUserInfo(username)
        }
    }

    suspend fun login(email: String, password:String,
                      loginErrorMessage: MutableLiveData<String>){
        withContext(context){
            userRepository.login(email, password, loginErrorMessage)
        }
    }
}