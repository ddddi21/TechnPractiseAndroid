package com.example.technpractiseandroid.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technpractiseandroid.some.AppBarItem
import javax.inject.Inject




class MainActivityVM @Inject constructor(): ViewModel() {

    val selectedTab = MutableLiveData(AppBarItem.HOME)

    val homeTabActive = MutableLiveData(true)
    val tasksTabActive = MutableLiveData(false)
    val createTabActive = MutableLiveData(false)
    val cabinetTabActive = MutableLiveData(false)

    private val tabMap = mapOf(
        AppBarItem.HOME to homeTabActive,
        AppBarItem.TASKS to tasksTabActive,
        AppBarItem.CABINET to cabinetTabActive,
        AppBarItem.CREATE to createTabActive
    )


    fun selectTab(newTab: AppBarItem) {
        selectedTab.value = newTab
        for ((tab, active) in tabMap) {
            active.value = tab == newTab
        }
    }

}