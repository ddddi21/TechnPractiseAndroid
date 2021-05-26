package com.example.technpractiseandroid.repository.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.repository.interfaces.TaskRepository
import com.example.technpractiseandroid.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.NullPointerException
import kotlin.coroutines.resume

class TaskRepositoryImpl(
    var db: FirebaseFirestore
) : TaskRepository {

    override suspend fun getTasksSize(
        currentUserId: String,
        size: MutableLiveData<Int>
    ): QuerySnapshot =
        suspendCancellableCoroutine { continuation ->
            db.collection(currentUserId)
                .get()
                .addOnSuccessListener {
                    if (it.lastOrNull() == null) {
                        size.value = 1
                        Log.d("find bug", "tasksSize ${it.size()}")
                    } else {
                        size.value = Integer.parseInt(it.lastOrNull()!!.id) + 1
                        Log.d("find bug", "lastOrNull ${it.lastOrNull()!!.id}")
                        Log.d("find bug", "tasksSize ${it.size()}")
                    }
                    continuation.resume(it)
                }
        }

    override suspend fun getTasksSizeForCount(
        currentUserId: String,
        size: MutableLiveData<Int>
    ): QuerySnapshot =
        suspendCancellableCoroutine { continuation ->
            db.collection(currentUserId)
                .get()
                .addOnSuccessListener {
                    if (it.lastOrNull() == null) {
                        size.value = 0
                        Log.d("find bug", "tasksSize ${it.size()}")
                    } else {
                        size.value = it.size()
                        Log.d("find bug", "tasksSize ${it.size()}")
                    }
                    continuation.resume(it)
                }
        }

    override suspend fun getTasksCountByImportanceTagImportant(
        currentUserId: String,
        size: MutableLiveData<Int>
    ):
            QuerySnapshot =
        suspendCancellableCoroutine { continuation ->
            db.collection(currentUserId)
                .whereEqualTo("importance", "fast")
                .get()
                .addOnSuccessListener {
                    if (it.lastOrNull() == null) {
                        size.value = 0
                        Log.d("find bug", "fast tasks count ${it.size()}")
                    } else {
                        size.value = it.size()
                        Log.d("find bug", "fast tasks count ${it.size()}")
                    }
                    continuation.resume(it)
                }
        }

    override suspend fun getTasksCountByImportanceTagMedium(
        currentUserId: String, size: MutableLiveData<Int>
    ): QuerySnapshot =
        suspendCancellableCoroutine { continuation ->
            db.collection(currentUserId)
                .whereEqualTo("importance", "medium")
                .get()
                .addOnSuccessListener {
                    if (it.lastOrNull() == null) {
                        size.value = 0
                        Log.d("find bug", "medium tasks count ${it.size()}")
                    } else {
                        size.value = it.size()
                        Log.d("find bug", "medium tasks count ${it.size()}")
                    }
                    continuation.resume(it)
                }
        }

    override suspend fun getTasksCountByImportanceTagLight(
        currentUserId: String, size: MutableLiveData<Int>
    ): QuerySnapshot =
        suspendCancellableCoroutine { continuation ->
            db.collection(currentUserId)
                .whereEqualTo("importance", "can wait")
                .get()
                .addOnSuccessListener {
                    if (it.lastOrNull() == null) {
                        size.value = 0
                        Log.d("find bug", "can wait tasks count ${it.size()}")
                    } else {
                        size.value = it.size()
                        Log.d("find bug", "can wait tasks count ${it.size()}")
                    }
                    continuation.resume(it)
                }
        }

    @InternalCoroutinesApi
    override suspend fun createTask(
        task: HashMap<String, String?>,
        taskCreatingErrorMessage: MutableLiveData<String>,
        currentUserId: String, tasksSize: MutableLiveData<Int>
    ): Void =
        suspendCancellableCoroutine { continuation ->
            db.collection(currentUserId)
                .document("${tasksSize.value}")
                .set(task, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("find bug", "DocumentSnapshot added")
                    tasksSize.value = +1
                    continuation.resume(it)
                }
                .addOnFailureListener { e ->
                    Log.w("find bug", "Error adding document", e)
                    taskCreatingErrorMessage.postValue("something wrong")
                    continuation.tryResumeWithException(e)
                }
        }

    @InternalCoroutinesApi
    override suspend fun loadTasks(
        currentUserId: String, myTasks: MutableList<Task>,
        tasksList: MutableLiveData<List<Task>>,
        errorMessage: MutableLiveData<String>
    ): QuerySnapshot =
        suspendCancellableCoroutine { continuatuaion ->
            db.collection(currentUserId)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents != null) {
                        for (document in documents) {
                            val task = Task(currentUserId!!, "", "", "", "", "", "")
                            Log.d("find bug", "${document.id} => ${document.data}")
                            task.apply {
                                name = document.data.get("name").toString()
                                description = document.data.get("description").toString()
                                tag = document.data.get("tag").toString()
                                importance = document.data.get("importance").toString()
                                date = document.data.get("date").toString()
                                time = document.data.get("time").toString()
                                myTasks.add(task)
                            }
                        }
                        tasksList.value = (myTasks)
                        Log.d("find bug", "DocumentSnapshot data: ${documents.size()}")
                        Log.d("find bug", "${myTasks.toString()}")
                        continuatuaion.resume(documents)
                    } else {
                        errorMessage.postValue("no such task")
                        Log.d("find bug", "no such document")
                        continuatuaion.tryResumeWithException(NullPointerException())
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("find bug", "load get failed with ", exception)
                    errorMessage.value = exception.message.toString()
                    continuatuaion.tryResumeWithException(exception)
                }

        }


    @InternalCoroutinesApi
    override suspend fun delete(
        currentUserId: String, isError: MutableLiveData<Boolean>,
        errorMessage: MutableLiveData<String>,
        position: Int
    ): Void =
        suspendCancellableCoroutine { continuatuaion ->
            Log.d("find bug", "delete position $position")
            db.collection(currentUserId).document("${position+1}")
                .delete()
                .addOnSuccessListener {
                    Log.d("find bug", "success delete")
                    continuatuaion.resume(it)
                    isError.value = false
                }
                .addOnFailureListener { exception ->
                    Log.d("find bug", "delete get failed with ", exception)
                    isError.value = true
                    errorMessage.value = exception.message.toString()
                    continuatuaion.tryResumeWithException(exception)
                }
        }
}

