package com.example.technpractiseandroid.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technpractiseandroid.interactors.TasksInteractor
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject


class CreateTaskVM @Inject constructor(
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

    var selectedDay = MutableLiveData<Int>()
    var selectedMonth = MutableLiveData<Int>()
    var selectedYear = MutableLiveData<Int>()

    var date: String? = null
    var time: String? = null

    private val taskSize = MutableLiveData<Int>()

    fun createTask() {
        if (taskName.value.isNullOrEmpty()) {
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
                        tasksInteractor.createTask(
                            task = task,
                            taskCreatingErrorMessage = taskCreatingErrorMessage,
                            currentUserId = currentUserId.toString(),
                            size = taskSize
                        )
                    }
                }

            }
        }
    }

    fun parseDateAndTime() {
        val dateTime = taskDueTo.value?.split(",")?.toTypedArray()
        date = dateTime?.get(0)
        time = dateTime?.get(1)
    }


}