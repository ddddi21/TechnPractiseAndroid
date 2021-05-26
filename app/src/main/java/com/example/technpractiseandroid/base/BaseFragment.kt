package com.example.technpractiseandroid.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.example.technpractiseandroid.R
import dagger.android.support.DaggerFragment;
import kotlinx.android.synthetic.main.tasks_fragment.*


public abstract class BaseFragment<T: ViewModel>: Fragment() {
lateinit var viewModel: T
}
val Fragment.navigationController
    get() = NavHostFragment.findNavController(this)


//val Fragment.onProfileClick
//    get() = NavHostFragment.findNavController(this).navigate(R.id.action_homeScreenFragment_to_profileFragment)
