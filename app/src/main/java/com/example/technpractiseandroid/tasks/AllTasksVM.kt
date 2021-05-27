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

    var newListForSortByTag = MutableLiveData<List<Task>>()


    var taskAdapter = TasksAdapter(myTasks)

    fun loadTasks() {
        viewModelScope.launch {
            try {
                myTasks.removeAll(myTasks)
                val currentUserId = mAuth.currentUser?.uid
                if (currentUserId != null) {
                    tasksInteractor.loadTasks(currentUserId, myTasks, tasksList, errorMessage)
                    isVisible.value = true
                    emptyList()
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
                    isVisible.value = true
                }
            } catch (throwable: Throwable) {
                Log.d("find bug", throwable.message.toString())
            } finally {
                isVisible.value = false
            }
        }
    }

    fun loadTasksByImportance(tag: String){
        val newList: MutableList<Task> = mutableListOf()
        tasksList.value?.forEach {  task ->
            if(task.importance == tag)
                newList.add(task)
        }
        newListForSortByTag.value = newList
        isNoTasksMessage.value = newListForSortByTag.value.isNullOrEmpty()
    }

    fun emptyList() {
        isNoTasksMessage.value = tasksList.value.isNullOrEmpty()
        Log.d("find bug", "${isNoTasksMessage.value} - when empty list")
    }

}