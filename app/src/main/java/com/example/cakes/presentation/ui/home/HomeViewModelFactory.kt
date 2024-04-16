package com.example.cakes.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.ProductListRepositoryImpl
import com.example.domain.usecase.homeusecase.GetProductListUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory : ViewModelProvider.Factory {

    private val productListRepositoryImpl = com.example.data.repository.ProductListRepositoryImpl()
    private val getProductListUseCase =
        com.example.domain.usecase.homeusecase.GetProductListUseCase(productListRepositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getProductListUseCase) as T
    }
}