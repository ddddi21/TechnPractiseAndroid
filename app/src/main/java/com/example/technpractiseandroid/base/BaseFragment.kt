package com.example.technpractiseandroid.base

import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment;


public abstract class BaseFragment<T: ViewModel>: DaggerFragment() {
lateinit var viewModel: T
}