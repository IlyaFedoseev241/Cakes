package com.example.cakes.ui.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakes.CakesApplication
import com.example.cakes.database.Cart
import com.example.cakes.database.MainDatabase
import com.example.cakes.database.RepositoryName
import kotlinx.coroutines.launch


class AddressInputViewModel : ViewModel() {
    val cartList: MutableLiveData<List<Cart>> = MutableLiveData()
    init {

        RepositoryName.get().cartList.observeForever {
            cartList.postValue(it)
        }
        getAllCart()
    }
    fun getAllCart(){
        viewModelScope.launch {
            RepositoryName.get().getAllCart()
        }
    }
}