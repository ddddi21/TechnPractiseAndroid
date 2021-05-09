package com.example.technpractiseandroid.base

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.auth.start.StartAuthFragment
import com.example.technpractiseandroid.di.components.AuthComponent
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class LoginActivity: AppCompatActivity() {
    private lateinit var controller: NavController

    lateinit var authComponent: AuthComponent




    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setContentView(R.layout.activity_splash_login)
        authComponent = MyMainApplication().authComponent()
        authComponent.inject(this)
        super.onCreate(savedInstanceState, persistentState)
        Timber.d("findBug: Start login activity")
        Log.d("findBug", "Start login activity")
//        controller =
//            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
//        setTheme(R.style.Some)

        //if has auth -> start main activity
        NavHostFragment.findNavController(StartAuthFragment())

//        supportFragmentManager.beginTransaction().replace(R.id.frame, StartAuthFragment()).commit()


    }

    override fun onStart() {
        super.onStart()
        Log.d("findBug", "Start login activity onstart")

    }
//    override fun onSupportNavigateUp(): Boolean = controller.navigateUp()

}