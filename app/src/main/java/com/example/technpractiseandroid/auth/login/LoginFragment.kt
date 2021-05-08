package com.example.technpractiseandroid.auth.login

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.di.VMFactory
import com.example.technpractiseandroid.di.components.MainAppComponent
import javax.inject.Inject

class LoginFragment: BaseFragment<LoginVM>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var loginVM: LoginVM =
        ViewModelProvider(this, viewModelFactory).get(LoginVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication().appComponent.inject(fragment = this@LoginFragment)
        super.onCreate(savedInstanceState)
    }
}