package com.example.cakes.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cakes.ProductListRepositoryImpl
import com.example.cakes.domain.usecase.homeusecase.GetProductListUseCase

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory : ViewModelProvider.Factory {

    private val productListRepositoryImpl = ProductListRepositoryImpl()
    private val getProductListUseCase = GetProductListUseCase(productListRepositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getProductListUseCase) as T
    }
}