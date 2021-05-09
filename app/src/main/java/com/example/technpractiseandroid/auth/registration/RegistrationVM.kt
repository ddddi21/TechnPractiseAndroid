package com.example.technpractiseandroid.auth.registration

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class RegistrationVM @Inject constructor(
private val mAuth: FirebaseAuth,
private val db: FirebaseFirestore
): ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val username = MutableLiveData("")

    val test = ""

    fun onRegistrationClick() {
        mAuth.createUserWithEmailAndPassword(email.value.toString(), password.value.toString())
            .addOnCompleteListener(this as Activity) { task -> //fix
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("reg", "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    user.sendEmailVerification()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("reg", "createUserWithEmail:failure", task.exception)

                }

            }

    }
}