package com.example.technpractiseandroid.repository.interfaces

import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.model.Task
import com.google.firebase.firestore.QuerySnapshot

interface TaskRepository {
    suspend fun createTask(task: HashMap<String, String?>,
                           taskCreatingErrorMessage: MutableLiveData<String>,
                           currentUserId: String,  tasksSize: MutableLiveData<Int>): Void

    suspend fun loadTasks(currentUserId: String, myTasks: MutableList<Task>,
                          tasksList: MutableLiveData<List<Task>>,
                          errorMessage: MutableLiveData<String>): QuerySnapshot

    suspend fun delete(currentUserId: String, isError: MutableLiveData<Boolean>,
                       errorMessage: MutableLiveData<String>, position:Int): Void

    suspend fun getTasksSize(currentUserId: String,
                             size:MutableLiveData<Int>): QuerySnapshot

    suspend fun getTasksSizeForCount(currentUserId: String, size:MutableLiveData<Int>): QuerySnapshot

    suspend fun getTasksCountByImportanceTagImportant(
        currentUserId: String,
        size:MutableLiveData<Int>): QuerySnapshot

    suspend fun getTasksCountByImportanceTagMedium(
        currentUserId: String,
        size:MutableLiveData<Int>): QuerySnapshot

    suspend fun getTasksCountByImportanceTagLight(
        currentUserId: String,
        size:MutableLiveData<Int>): QuerySnapshot

}