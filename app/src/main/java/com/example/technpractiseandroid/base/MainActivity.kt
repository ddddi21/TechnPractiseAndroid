package com.example.technpractiseandroid.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.databinding.ActivityMainBinding
import com.example.technpractiseandroid.some.AppBarItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    //nado li v dagger
//    private lateinit var controller: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var currentFragment = 0

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
        val binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.lifecycleOwner = this
//        binding.vm = vm
        NavigationUI.setupWithNavController(bottomVagView, navController)

    }


    private fun navigateTo(@IdRes fragmentId: Int) {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navHostFragment.navController.popBackStack(currentFragment, true)
        currentFragment = fragmentId
        navHostFragment.navController.navigate(fragmentId)
    }
}


