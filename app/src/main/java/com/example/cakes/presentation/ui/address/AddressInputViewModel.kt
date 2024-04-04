package com.example.cakes.presentation.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakes.api.RequestOrderData
import com.example.cakes.Cart
import com.example.cakes.DatabaseRepository
import com.example.cakes.domain.usecase.addressusecase.PostOrderUseCase
import kotlinx.coroutines.launch


class AddressInputViewModel(private val postOrderUseCase: PostOrderUseCase) : ViewModel() {

    private var _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token
    fun postOrder(requestOrderData: RequestOrderData){
        viewModelScope.launch {
            _token.value = postOrderUseCase.execute(requestOrderData).token
        }
    }
    val cartList: MutableLiveData<List<Cart>> = MutableLiveData()



    init {

        DatabaseRepository.get().cartList.observeForever {
            cartList.postValue(it)
        }
        getAllCart()
    }

    fun getAllCart() {
        viewModelScope.launch {
            DatabaseRepository.get().getAllCart()
        }
    }


}