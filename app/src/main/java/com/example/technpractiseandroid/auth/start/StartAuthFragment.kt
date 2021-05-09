package com.example.technpractiseandroid.auth.start

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.auth.registration.RegistrationVM
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.databinding.FragmentStartAuthBinding
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import com.example.technpractiseandroid.base.navigationController
import timber.log.Timber


class StartAuthFragment: BaseFragment<StartAuthVM>() {
    //спросить, сюда ли или в вм (наверное сюда)
    private var mAuth: FirebaseAuth?= null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viemodel: StartAuthVM =
        ViewModelProvider(this, viewModelFactory).get(StartAuthVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("findBug: Start auth fragment")
        Log.d("findBug", "Start auth fragment")
        MyMainApplication().authComponent().inject(this)
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        if(mAuth?.currentUser != null){
//            //user sign in
//            //updateUI(currentUser)
//        } else{
//            // user log out
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStartAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner= this
        binding.vm = viemodel

        navigate(view as ViewGroup)
        return binding.root
    }

    private fun navigate(root: ViewGroup){
        val toLogin = root.findViewById<Button>(R.id.btn_start_auth_to_sign_in)
        val toRegistration = root.findViewById<Button>(R.id.btn_start_auth_to_sign_up)

        toLogin.setOnClickListener {
            onLoginClick()
        }
        toRegistration.setOnClickListener {
            onRegistrationClick()
        }
    }

    private fun onLoginClick(){
        navigationController.navigate(R.id.action_startAuthFragment_to_loginFragment)
    }

    private fun onRegistrationClick(){
        navigationController.navigate(R.id.action_startAuthFragment_to_registrationFragment)
    }
}