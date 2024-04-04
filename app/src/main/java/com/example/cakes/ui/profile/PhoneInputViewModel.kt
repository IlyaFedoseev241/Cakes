package com.example.cakes.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cakes.database.RepositoryName
import com.example.cakes.database.User
import kotlinx.coroutines.launch

class PhoneInputViewModel : ViewModel() {

    fun insertUser(user: User){
        viewModelScope.launch {
            RepositoryName.get().insertUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch {
            RepositoryName.get().deleteUser(user)
        }
    }

}