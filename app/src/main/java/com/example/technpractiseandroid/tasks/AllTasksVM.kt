package com.example.technpractiseandroid.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.interactors.TasksInteractor
import com.example.technpractiseandroid.model.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTasksVM @Inject constructor(
    var mAuth: FirebaseAuth,
    var tasksInteractor: TasksInteractor
) : ViewModel() {
    val today = MutableLiveData(false)
    val myTasks: MutableList<Task> = mutableListOf()
    val tasksList = MutableLiveData<List<Task>>()
    private var errorMessage = MutableLiveData<String>()
    private var errorMessageWhenDelete = MutableLiveData<String>()
    var isErrorWhenDelete = MutableLiveData<Boolean>()
    var isVisible = MutableLiveData(true)
    var isNoTasksMessage = MutableLiveData<Boolean>()


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
                    tasksInteractor.deleteTask(
                        currentUserId,
                        isError = isErrorWhenDelete,
                        errorMessage = errorMessageWhenDelete,
                        position = position
                    )
                    taskAdapter.removeItem(position)
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            }
        }
    }


    fun emptyList() {
        isNoTasksMessage.value = myTasks.size == 0
        Log.d("find bug", "${isNoTasksMessage.value} - when empty list")
    }

}