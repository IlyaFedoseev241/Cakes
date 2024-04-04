package com.example.cakes.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cakes.database.RepositoryName
import com.example.cakes.database.User
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val user = liveData {
        emit(RepositoryName.get().loadUser())
    }
    init {
        RepositoryName.get()
    }
    fun updateUser(user: User){
        viewModelScope.launch {
            RepositoryName.get().updateUser(user)
        }
    }
}