package com.example.cakes.presentation.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.CartData
import com.example.data.repository.DatabaseRepository
import com.example.domain.model.OrderRequestDomain
import com.example.domain.usecase.addressusecase.PostOrderUseCase
import kotlinx.coroutines.launch


class AddressInputViewModel(private val postOrderUseCase: com.example.domain.usecase.addressusecase.PostOrderUseCase) : ViewModel() {

    private var _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token
    fun postOrder(orderRequestDomain: com.example.domain.model.OrderRequestDomain){
        viewModelScope.launch {
            _token.value = postOrderUseCase.execute(orderRequestDomain).token
        }
    }
    val cartDataList: MutableLiveData<List<com.example.data.model.CartData>> = MutableLiveData()



    init {

        com.example.data.repository.DatabaseRepository.get().cartDataList.observeForever {
            cartDataList.postValue(it)
        }
        getAllCart()
    }

    fun getAllCart() {
        viewModelScope.launch {
            com.example.data.repository.DatabaseRepository.get().getAllCart()
        }
    }


}