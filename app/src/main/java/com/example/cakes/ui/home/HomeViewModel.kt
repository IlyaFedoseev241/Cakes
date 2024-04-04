package com.example.cakes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cakes.database.Cart
import com.example.cakes.database.RepositoryName
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

   fun insertItemToCart(cart: Cart){
       viewModelScope.launch {
           RepositoryName.get().insertItemToCart(cart)
       }
   }
}