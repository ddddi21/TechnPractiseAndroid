package com.example.technpractiseandroid.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.technpractiseandroid.R
import com.google.firebase.auth.FirebaseAuth

class AuthCheckingFragment: Fragment() {
    //спросить, сюда ли или в вм (наверное сюда)
    private var mAuth: FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        if(mAuth?.currentUser != null){
            //user sign in
            //updateUI(currentUser)
        } else{
            // user log out
        }
    }
}