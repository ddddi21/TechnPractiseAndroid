package com.example.technpractiseandroid.auth.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.auth.registration.RegistrationVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.base.LoginActivity
import com.example.technpractiseandroid.base.MainActivity
import com.example.technpractiseandroid.databinding.FragmentStartAuthBinding
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import com.example.technpractiseandroid.base.navigationController
import timber.log.Timber


class StartAuthFragment: BaseFragment<StartAuthVM>() {
    //спросить, сюда ли или в вм (наверное сюда)
//    private var mAuth: FirebaseAuth?= null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viemodel: StartAuthVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("findBug: Start auth fragment")
        Log.d("findBug", "Start auth fragment")
        MyMainApplication.appComponent.inject(this)
        viemodel =ViewModelProvider(this, viewModelFactory).get(StartAuthVM::class.java)
//        viemodel = this.viewModels(viewModelFactory)

       viemodel.mAuth = FirebaseAuth.getInstance()
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        Timber.d("current user: user")

            if (viemodel.mAuth?.currentUser != null) {
                startMainActivity()
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStartAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner= this
        binding.vm = viemodel

        binding.btnStartAuthToSignIn.setOnClickListener {
            onLoginClick()
        }
        binding.btnStartAuthToSignUp.setOnClickListener {
            onRegistrationClick()
        }
        return binding.root
    }


    private fun onLoginClick(){
        navigationController.navigate(R.id.action_startAuthFragment_to_loginFragment)
    }

    private fun onRegistrationClick(){
        navigationController.navigate(R.id.action_startAuthFragment_to_registrationFragment)
    }

    fun startMainActivity(){
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }
}