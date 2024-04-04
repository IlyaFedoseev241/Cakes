package com.example.cakes.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cakes.DatabaseRepository
import com.example.cakes.User
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val user = liveData {
        emit(DatabaseRepository.get().loadUser())
    }
    init {
        DatabaseRepository.get()
    }
    fun updateUser(user: User){
        viewModelScope.launch {
            DatabaseRepository.get().updateUser(user)
        }
    }
}