package com.example.cakes.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CartDomain
import com.example.domain.usecase.cartusecase.DeleteItemUseCase
import com.example.domain.usecase.cartusecase.GetCartListUseCase
import com.example.domain.usecase.cartusecase.GetFinalPriceUseCase
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartListUseCase: com.example.domain.usecase.cartusecase.GetCartListUseCase,
    private val getFinalPriceUseCase: com.example.domain.usecase.cartusecase.GetFinalPriceUseCase,
    private val deleteItemUseCase: com.example.domain.usecase.cartusecase.DeleteItemUseCase
) : ViewModel() {
    val finishPrice = liveData {
        emit(getFinalPriceUseCase.execute())
    }
    val cartList = liveData {
        emit(getCartListUseCase.execute())
    }
    fun deleteItemFromCart(cartDomain: com.example.domain.model.CartDomain){
        viewModelScope.launch {
            deleteItemUseCase.execute(cartDomain)
        }
    }

}