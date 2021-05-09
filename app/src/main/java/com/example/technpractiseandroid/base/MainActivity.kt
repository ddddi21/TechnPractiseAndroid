package com.example.technpractiseandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.technpractiseandroid.R
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : AppCompatActivity() {

    //nado li v dagger
    private lateinit var controller: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        controller =
//            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController

//        controller.addOnDestinationChangedListener { _, destination, _ ->
//            toolbar.title = when (destination.id) {
//                R.id.homeFragment -> getString(R.string.home)
//                R.id.chatFragment -> getString(R.string.chat)
//                R.id.profileFragment -> getString(R.string.profile)
//                R.id.notificationFragment -> getString(R.string.notifications)
//                R.id.settingsFragment -> getString(R.string.settings)
//                else -> getString(R.string.app_name)
//            }
//        }
//
//        bnv_main.setupWithNavController(controller)
    }

//    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()



}