package com.example.cakes.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DatabaseRepository
import com.example.data.database.User
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val user = liveData {
        emit(com.example.data.repository.DatabaseRepository.get().loadUser())
    }
    init {
        com.example.data.repository.DatabaseRepository.get()
    }
    fun updateUser(user: com.example.data.database.User){
        viewModelScope.launch {
            com.example.data.repository.DatabaseRepository.get().updateUser(user)
        }
    }
}