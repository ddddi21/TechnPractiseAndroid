package com.example.technpractiseandroid.tasks

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.interactors.TasksInteractor
import com.example.technpractiseandroid.user.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTasksVM @Inject constructor(
    var db: FirebaseFirestore,
    var mAuth: FirebaseAuth,
    var tasksInteractor: TasksInteractor
) :ViewModel() {
    val today = MutableLiveData(false)
    val myTasks: MutableList<Task> = mutableListOf()
    val tasksList = MutableLiveData<List<Task>>()
    private var errorMessage = MutableLiveData<String>()
    private var errorMessageWhenDelete = MutableLiveData<String>()
    var isErrorWhenDelete= MutableLiveData<Boolean> ()
    private var noTasks = false
    var isVisible = MutableLiveData(true)
    var isNoTasksMessage =  MutableLiveData<Boolean>()


    var taskAdapter = TasksAdapter(myTasks)

    fun loadTasks() {
        viewModelScope.launch {
            try {
                val currentUserId = mAuth.currentUser?.uid
                if (currentUserId != null) {
                    tasksInteractor.loadTasks(currentUserId, myTasks, tasksList, errorMessage)
                    isVisible.value = true
                    isNoTasksMessage.value = false
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            } finally {
                isVisible.value = false
            }
        }
    }

    fun deleteTask(position: Int) {
        viewModelScope.launch {
                try {
                    val currentUserId = mAuth.currentUser?.uid
                    if (currentUserId != null) {
                        tasksInteractor.deleteTask(currentUserId,
                            isError = isErrorWhenDelete,
                            errorMessage = errorMessageWhenDelete,
                            position = position)
                    }
                } catch (throwable: Throwable) {
                    Log.d("find bug", throwable.message.toString())
                }
            }
    }


    fun emptyList(){
        isNoTasksMessage.value = myTasks.size.equals(0)
        Log.d("find bug","${isNoTasksMessage.value} - when empty list" )
    }

}