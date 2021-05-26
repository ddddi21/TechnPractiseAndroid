package com.example.technpractiseandroid.tasks

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.interactors.TasksInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CreateTaskVM @Inject constructor(
var db: FirebaseFirestore,
var mAuth: FirebaseAuth,
var tasksInteractor: TasksInteractor
) : ViewModel() {
    val taskDueTo = MutableLiveData(String())
    var taskName = MutableLiveData(String())
    var taskDescription = MutableLiveData(String())
    var selectedTag = MutableLiveData<String>()
    var isTagSelected = MutableLiveData<Boolean>(false)

    var selectedImportantTag = MutableLiveData<String>()
    var isImportnantTagSelected = MutableLiveData<Boolean>(false)
    var taskCreatingErrorMessage = MutableLiveData<String>()

    var date: String ?= null
    var time: String ?= null

    private val taskSize = MutableLiveData<Int>()
//    init {
//        getListSize()
//    }
//
//    fun getListSize() {
//        db.collection(
//            mAuth.currentUser!!.uid
//        ).get().addOnSuccessListener {
//            taskSize.value = it.size()
//        }
//    }



    fun createTask(){
        if(taskName.value.isNullOrEmpty()){
            taskCreatingErrorMessage.value = "Empty task name"
            return
        } else {
            if (isImportnantTagSelected.value == false) {
                taskCreatingErrorMessage.value = "Choose importance of the task"
                return
            } else {
                if (taskDueTo.value.isNullOrEmpty()) {
                    taskCreatingErrorMessage.value = "Specify the deadline for your task"
                    return
                } else {
                    parseDateAndTime()
                    val currentUserId = mAuth.currentUser?.uid
                    val task = hashMapOf(
                        "uid" to currentUserId,
                        "name" to taskName.value,
                        "description" to taskDescription.value,
                        "tag" to selectedTag.value,
                        "importance" to selectedImportantTag.value,
                        "date" to date,
                        "time" to time
                    )
                    viewModelScope.launch {
                        tasksInteractor.createTask(task = task,
                            taskCreatingErrorMessage = taskCreatingErrorMessage,
                            currentUserId = currentUserId.toString(),
                            size = taskSize
                        )
                    }
                }

            }
        }
    }

    fun parseDateAndTime(){
        var dateTime = taskDueTo.value?.split(",")?.toTypedArray()
        date = dateTime?.get(0)
        time = dateTime?.get(1)

    }



}