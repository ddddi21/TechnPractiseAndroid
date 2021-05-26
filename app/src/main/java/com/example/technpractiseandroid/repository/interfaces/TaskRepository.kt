package com.example.technpractiseandroid.repository.interfaces

import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.user.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface TaskRepository {
    suspend fun createTask(task: HashMap<String, String?>, taskCreatingErrorMessage: MutableLiveData<String>): DocumentReference

    suspend fun loadTasks(currentUserId: String, myTasks: MutableList<Task>,
                          tasksList: MutableLiveData<List<Task>>,
                          errorMessage: MutableLiveData<String>): QuerySnapshot
}