package com.example.cakes.ui.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakes.database.Cart
import com.example.cakes.database.RepositoryName
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    val finishPrice:MutableLiveData<Double> = MutableLiveData()
    val cartList: MutableLiveData<List<Cart>> = MutableLiveData()
    init {
        RepositoryName.get().finishPrice.observeForever{
            finishPrice.postValue(it)
        }
        RepositoryName.get().cartList.observeForever {
            cartList.postValue(it)
        }
        getAllCart()
        getTotalPrice()
    }
    fun getAllCart(){
        viewModelScope.launch {
            RepositoryName.get().getAllCart()
        }
    }
    fun getTotalPrice(){
        viewModelScope.launch {
            RepositoryName.get().getTotalPrice()
        }
    }
}