package com.example.cakes.presentation.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cakes.domain.usecase.cartusecase.GetCartListUseCase
import com.example.cakes.domain.usecase.cartusecase.GetFinalPriceUseCase

class CartViewModel(private val getCartListUseCase: GetCartListUseCase, private val getFinalPriceUseCase: GetFinalPriceUseCase) : ViewModel() {
    val finishPrice = liveData {
        emit(getFinalPriceUseCase.execute())
    }
    val cartList = liveData {
        emit(getCartListUseCase.execute())
    }
   /* init {
        DatabaseRepository.get().finishPrice.observeForever{
            finishPrice.postValue(it)
        }
        DatabaseRepository.get().cartList.observeForever {
            cartList.postValue(it)
        }
        getAllCart()
        getTotalPrice()
    }
    fun getAllCart(){
        viewModelScope.launch {
            DatabaseRepository.get().getAllCart()
        }
    }
    fun getTotalPrice(){
        viewModelScope.launch {
            DatabaseRepository.get().getTotalPrice()
        }
    }*/
}