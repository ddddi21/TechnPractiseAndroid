package com.example.technpractiseandroid.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var vm: MainActivityVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyMainApplication.appComponent.inject(this)
        vm = ViewModelProvider(this, viewModelFactory).get(MainActivityVM::class.java)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment? ?: return
        val navController = host.navController
        val bottomVagView = findViewById<BottomNavigationView>(R.id.bnv_main)
        NavigationUI.setupWithNavController(bottomVagView, navController)
    }
}

fun Activity.startLoginActivity() {
    val intent = Intent(this, LoginActivity::class.java)
    application?.startActivity(intent)
    finish()
}



