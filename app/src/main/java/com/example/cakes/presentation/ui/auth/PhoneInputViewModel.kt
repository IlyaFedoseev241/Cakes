package com.example.cakes.presentation.ui.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakes.DatabaseRepository
import com.example.cakes.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class PhoneInputViewModel : ViewModel() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mNumberPhone:String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    fun firebasePhoneProvider(callbacks: PhoneInputFragment.Callbacks, context:Context) {
        mAuth = FirebaseAuth.getInstance()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.e("TAG", "Phone is correct")
                    } else {
                        Log.e("TAG", "Phone isn't correct")
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.e("TAG", p0.message.toString())
                Toast.makeText(context, p0.message.toString(), Toast.LENGTH_SHORT).show()
            }
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                callbacks.inputCode(mNumberPhone, id)
                Log.e("NUMBER_TAG",mNumberPhone)
            }
        }
    }

     fun sendCode(phone:String,activity: Activity){
        if(phone.isEmpty()){
            Log.e("TAG","Введите номер телефона")
        }else {
            authUser(phone,activity)
        }
    }

    private fun authUser(phone: String,activity:Activity){
        mNumberPhone = phone
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(mNumberPhone)
            .setTimeout(60, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(mCallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    fun insertUser(user: User){
        viewModelScope.launch {
            DatabaseRepository.get().insertUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch {
            DatabaseRepository.get().deleteUser(user)
        }
    }

}