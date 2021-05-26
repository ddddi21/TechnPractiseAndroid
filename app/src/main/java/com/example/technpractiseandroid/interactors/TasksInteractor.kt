package com.example.technpractiseandroid.interactors

import androidx.lifecycle.MutableLiveData
import com.example.technpractiseandroid.repository.interfaces.TaskRepository
import com.example.technpractiseandroid.user.Task
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class TasksInteractor @Inject constructor(
    private var taskRepository: TaskRepository,
    @Named("IO")private val context: CoroutineContext
) {
    suspend fun loadTasks(currentUserId: String, myTasks: MutableList<Task>,
                          tasksList: MutableLiveData<List<Task>>,
                          errorMessage: MutableLiveData<String>){
                              withContext(context){
                                  taskRepository.loadTasks(currentUserId, myTasks, tasksList,
                                  errorMessage)
                              }
                          }

    suspend fun createTask(task: HashMap<String, String?>,
                           taskCreatingErrorMessage: MutableLiveData<String>,
                           currentUserId: String, size:MutableLiveData<Int>
    ) {
        withContext(context){
            getTasksSize(currentUserId, size)
            taskRepository.createTask(task, taskCreatingErrorMessage, currentUserId, size)
        }
    }
    suspend fun deleteTask(currentUserId: String, isError: MutableLiveData<Boolean>,
                           errorMessage: MutableLiveData<String>, position: Int) {
        withContext(context){
            taskRepository.delete(currentUserId,isError, errorMessage, position)
        }
    }

    suspend fun getTasksSize(currentUserId: String, size:MutableLiveData<Int>){
        withContext(context){
              taskRepository.getTasksSize(currentUserId, size)
        }
    }
}