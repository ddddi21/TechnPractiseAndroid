package com.example.technpractiseandroid.repository.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.repository.interfaces.TaskRepository
import com.example.technpractiseandroid.user.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.NullPointerException
import kotlin.coroutines.resume

class TaskRepositoryImpl(
    var db: FirebaseFirestore
    ): TaskRepository {

    @InternalCoroutinesApi
    override suspend fun createTask(task: HashMap<String, String?>,
                                    taskCreatingErrorMessage: MutableLiveData<String>): DocumentReference =
        suspendCancellableCoroutine { continuation ->
            db.collection("tasks")
                .add(task)
                .addOnSuccessListener {
                    Log.d("find bug", "DocumentSnapshot added")
                    continuation.resume(it)
                }
                .addOnFailureListener { e ->
                    Log.w("find bug", "Error adding document", e)
                    taskCreatingErrorMessage.postValue("something wrong")
                    continuation.tryResumeWithException(e)
                }
        }

    @InternalCoroutinesApi
    override suspend fun loadTasks(currentUserId: String, myTasks: MutableList<Task>,
                                   tasksList: MutableLiveData<List<Task>>,
                                   errorMessage: MutableLiveData<String>): QuerySnapshot=
        suspendCancellableCoroutine{ continuatuaion ->
        db.collection("tasks").whereEqualTo("uid", currentUserId)
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
//                        taskAdapter = TasksAdapter(tasksList.value!!)
                    Log.d("find bug", "DocumentSnapshot data: ${documents.size()}")
                    Log.d("find bug", "${myTasks.toString()}")
                    continuatuaion.resume(documents)
                } else {
                    errorMessage.postValue("no such document")
                    Log.d("find bug", "no such document")
                    continuatuaion.tryResumeWithException(NullPointerException())
                }
            }
            .addOnFailureListener { exception ->
                Log.d("find bug", "get failed with ", exception)
                continuatuaion.tryResumeWithException(exception)
            }

    }
    }

