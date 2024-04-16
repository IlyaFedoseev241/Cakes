package com.example.cakes.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.CartListRepositoryImpl
import com.example.domain.usecase.cartusecase.DeleteItemUseCase
import com.example.domain.usecase.cartusecase.GetCartListUseCase
import com.example.domain.usecase.cartusecase.GetFinalPriceUseCase

class CartViewModelFactory: ViewModelProvider.Factory {
    private var cartListRepositoryImp = com.example.data.repository.CartListRepositoryImpl()
    private var getCartListUseCase =
        com.example.domain.usecase.cartusecase.GetCartListUseCase(cartListRepositoryImp)
    private var getFinalPriceUseCase =
        com.example.domain.usecase.cartusecase.GetFinalPriceUseCase(cartListRepositoryImp)
    private var deleteItemUseCase =
        com.example.domain.usecase.cartusecase.DeleteItemUseCase(cartListRepositoryImp)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(getCartListUseCase,getFinalPriceUseCase,deleteItemUseCase) as T
    }
}