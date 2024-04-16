package com.example.cakes.presentation.ui.address

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.api.ApiService
import com.example.domain.usecase.addressusecase.PostOrderUseCase

class AddressInputViewModelFactory(private val apiService: com.example.data.api.ApiService) : ViewModelProvider.Factory {
    private val orderRepositoryImpl = com.example.data.repository.OrderRepositoryImpl(apiService)
    private val postOrderUseCase =
        com.example.domain.usecase.addressusecase.PostOrderUseCase(orderRepositoryImpl)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressInputViewModel::class.java)) {
            Log.e("Tag","Factory is create ")
            return AddressInputViewModel(postOrderUseCase) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}