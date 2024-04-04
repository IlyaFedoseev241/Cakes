package com.example.cakes.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cakes.CartListRepositoryImpl
import com.example.cakes.domain.usecase.cartusecase.GetCartListUseCase
import com.example.cakes.domain.usecase.cartusecase.GetFinalPriceUseCase

class CartViewModelFactory: ViewModelProvider.Factory {
    private var cartListRepositoryImp = CartListRepositoryImpl()
    private var getCartListUseCase = GetCartListUseCase(cartListRepositoryImp)
    private var getFinalPriceUseCase = GetFinalPriceUseCase(cartListRepositoryImp)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(getCartListUseCase,getFinalPriceUseCase) as T
    }
}