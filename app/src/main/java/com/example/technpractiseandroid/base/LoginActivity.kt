package com.example.technpractiseandroid.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.technpractiseandroid.R
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("findBug: Start login activity")
        Log.d("findBug", "Start login activity")
        setContentView(R.layout.activity_splash_login)
    }

    override fun onStart() {
        super.onStart()
        Log.d("findBug", "Start login activity onstart")
    }
}

fun Activity.startApp() {
    val intent = Intent(this, MainActivity::class.java)
    application?.startActivity(intent)
    finish()
}