package com.example.technpractiseandroid.auth.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.auth.login.LoginVM
import com.example.technpractiseandroid.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegistrationFragment: BaseFragment<RegistrationVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mAuth: FirebaseAuth


    private var registrationVM: RegistrationVM =
        ViewModelProvider(this, viewModelFactory).get(RegistrationVM::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication().appComponent.inject(fragment = this@RegistrationFragment)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

fun createAccount() {


}
}