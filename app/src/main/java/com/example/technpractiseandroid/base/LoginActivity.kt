package com.example.technpractiseandroid.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.technpractiseandroid.R
import timber.log.Timber

class LoginActivity: AppCompatActivity() {
    private lateinit var controller: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        MyMainApplication().appComponent.inject(this)
        Timber.d("findBug: Start login activity")
        Log.d("findBug", "Start login activity")
        setContentView(R.layout.activity_splash_login)

//        controller =
//            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
//        setTheme(R.style.Some)

        //if has auth -> start main activity
//        NavHostFragment.findNavController(StartAuthFragment())

//        supportFragmentManager.beginTransaction().replace(R.id.frame, StartAuthFragment()).commit()


    }

    override fun onStart() {
        super.onStart()
        Log.d("findBug", "Start login activity onstart")

    }
//    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()

}