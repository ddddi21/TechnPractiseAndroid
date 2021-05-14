package com.example.technpractiseandroid.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.databinding.ActivityMainBinding
import com.example.technpractiseandroid.databinding.HomePageFragmentBinding
import com.example.technpractiseandroid.main.HomeScreenVM
import com.example.technpractiseandroid.some.AppBarItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
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
//        val host: NavHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment? ?: return
//        val navController = host.navController
//        setUpBottomNav(navController)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        binding.lifecycleOwner = this
//        binding.vm = vm
//        binding.bnvMain.setupWithNavController(navController)

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

    }


    private fun navigateTo(@IdRes fragmentId: Int) {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navHostFragment.navController.popBackStack(currentFragment, true)
        currentFragment = fragmentId
        navHostFragment.navController.navigate(fragmentId)
    }

    private fun handleAppBarSelectedItem(item:AppBarItem) {
        when (item) {
            AppBarItem.HOME -> {
                navigateTo(R.id.homeScreenFragment)
            }

//            AppBarItem.TASKS -> {
//                navigateTo(R.id.messagesFragment)
//            }
            AppBarItem.CABINET -> {
                navigateTo(R.id.profileFragment)
            }
        }
    }
}


//    private fun setUpBottomNav(navController: NavController) {
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bnv_main)
//        bottomNav?.setupWithNavController(navController)
//    }
