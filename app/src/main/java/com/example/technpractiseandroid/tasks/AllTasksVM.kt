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
    private val docSize =0
    private var errorMessage = MutableLiveData<String>()
    private var noTasks = false
    var isVisible = true


    var taskAdapter = TasksAdapter(myTasks)


    fun loadTasks(){
        viewModelScope.launch {
            try {
                val currentUserId = mAuth.currentUser?.uid
                if (currentUserId != null) {
                    tasksInteractor.loadTasks(currentUserId, myTasks, tasksList, errorMessage)
                    isVisible = true
                }
            } catch (throwable: Throwable){
                Log.d("find bug", throwable.message.toString())
            } finally {
                isVisible = false
            }
        }

//            db.collection("tasks").whereEqualTo("uid", "$currentUserId")
//                .get()
//                .addOnSuccessListener {
//                        documents ->
//                    if (documents!= null) {
//                            for (document in documents){
//                                 var task = Task(currentUserId!!,"","","","","","")
//                                Log.d("find bug","${document.id} => ${document.data}")
//                                task.apply {
//                                    name = document.data.get("name").toString()
//                                    description = document.data.get("description").toString()
//                                    tag = document.data.get("tag").toString()
//                                    importance = document.data.get("importance").toString()
//                                    date = document.data.get("date").toString()
//                                    time = document.data.get("time").toString()
//                                    myTasks.add(task)
//                                }
//                            }
//                        tasksList.value = (myTasks)
////                        taskAdapter = TasksAdapter(tasksList.value!!)
//                        Log.d("find bug", "DocumentSnapshot data: ${documents.size()}")
//                        Log.d("find bug", "${myTasks.toString()}")
//
//                    } else {
//                        noTasks = true
//                        errorMessage = "no such document"
//                        Log.d("find bug", "no such document")
//                    }
//                }
//                .addOnFailureListener{
//                        exception ->
//                        Log.d("find bug", "get failed with ", exception)
//                    }
        }
}