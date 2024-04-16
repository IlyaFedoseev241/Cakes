package com.example.cakes.presentation.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DatabaseRepository
import com.example.data.database.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch

class CodeConfirmViewModel : ViewModel() {
    private lateinit var mAuth: FirebaseAuth
    fun enterCode(id: String, code: String, callbacks: CodeConfirmFragment.Callbacks) {
        mAuth = FirebaseAuth.getInstance()
        val credential = PhoneAuthProvider.getCredential(id, code)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.e("TAG", "Добро пожаловать")
                callbacks.showProfile()
            } else {
                Log.e("TAG", "Ошибка авторизации")
            }
        }
    }

    fun insertUser(user: com.example.data.database.User){
        viewModelScope.launch {
            com.example.data.repository.DatabaseRepository.get().insertUser(user)
        }
    }
}