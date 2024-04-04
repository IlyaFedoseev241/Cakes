package com.example.cakes.presentation.ui.address

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cakes.OrderRepositoryImpl
import com.example.cakes.api.ApiService
import com.example.cakes.domain.usecase.addressusecase.PostOrderUseCase

class AddressInputViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    private val orderRepositoryImpl = OrderRepositoryImpl(apiService)
    private val postOrderUseCase = PostOrderUseCase(orderRepositoryImpl)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressInputViewModel::class.java)) {
            Log.e("Tag","Factory is create ")
            return AddressInputViewModel(postOrderUseCase) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}