package com.example.technpractiseandroid.main

import android.util.Log
import androidx.lifecycle.*
import com.example.technpractiseandroid.interactors.TasksInteractor
import com.example.technpractiseandroid.repository.WordRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenVM @Inject constructor(
    val mAuth: FirebaseAuth,
    var wordRepository: WordRepository,
    val tasksInteractor: TasksInteractor
) : ViewModel() {
    var words = MutableLiveData<String>()
    val username = MutableLiveData("")

    var tasksCountInt = MutableLiveData<Int>()
    var tasksCountString = MutableLiveData("")

    var tasksCountFastInt = MutableLiveData<Int>(0)
    var tasksCountFastString = MutableLiveData("")

    var tasksCountMediumInt = MutableLiveData<Int>(0)
    var tasksCountMediumString = MutableLiveData("")

    var tasksCountLightInt = MutableLiveData<Int>(0)
    var tasksCountLightString = MutableLiveData("")

    var currentUser = MutableLiveData<FirebaseUser>()

    var isVisibleProgress = MutableLiveData<Boolean>()

    init {
        wordRepository.getWords()
        loadTasksCount()
        loadTasksCountByImportanceImportant()
        loadTasksCountByImportanceMedium()
        loadTasksCountByImportanceLight()
        isVisibleProgress.value = true
    }

    val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        if (firebaseAuth.currentUser != null) {
            currentUser.value = firebaseAuth.currentUser
            Log.d("find bug", "${firebaseAuth.currentUser}")
            username.value = "Hello, ${firebaseAuth.currentUser!!.displayName}!"
        }
    }

    fun setUsername() {
        mAuth.addAuthStateListener(authStateListener)
    }

    fun loadTasksCount() {
        viewModelScope.launch {
            try {
                mAuth.currentUser?.let {
                    tasksInteractor.getTasksSizeForCount(it.uid, tasksCountInt)
                    tasksCountString.value = tasksCountInt.value.toString()
                    isVisibleProgress.value = true
                    Log.d("find bug", "tasks count home page #${tasksCountString.value}")
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            } finally {
                isVisibleProgress.value = false
            }
        }
    }

    fun loadTasksCountByImportanceImportant() {
        viewModelScope.launch {
            try {
                mAuth.currentUser?.let {
                    tasksInteractor.getTasksCountByImportanceTagImportant(it.uid, tasksCountFastInt)
                    tasksCountFastString.value = tasksCountFastInt.value.toString()
                    Log.d("find bug", "fast tasks home page #${tasksCountFastString.value}")
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            }
        }
    }

    fun loadTasksCountByImportanceMedium() {
        viewModelScope.launch {
            try {
                mAuth.currentUser?.let {
                    tasksInteractor.getTasksCountByImportanceTagMedium(it.uid, tasksCountMediumInt)
                    tasksCountMediumString.value = tasksCountMediumInt.value.toString()
                    Log.d("find bug", "medium tasks home page #${tasksCountMediumString.value}")
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            }
        }
    }

    fun loadTasksCountByImportanceLight() {
        viewModelScope.launch {
            try {
                mAuth.currentUser?.let {
                    tasksInteractor.getTasksCountByImportanceTagLight(it.uid, tasksCountLightInt)
                    tasksCountLightString.value = tasksCountLightInt.value.toString()
                    Log.d("find bug", "fast tasks home page #${tasksCountLightString.value}")
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            }
        }
    }

    fun randomSupport() {
        val id = (1..15).random()
        Log.d("find bug", "random words #$id")
        val wordsFromRepo = wordRepository.getWordById(id)
        if (wordsFromRepo != null) {
            words.value = wordsFromRepo.text
        } else {
            words.value = "Have a nice day!"
        }
    }
}