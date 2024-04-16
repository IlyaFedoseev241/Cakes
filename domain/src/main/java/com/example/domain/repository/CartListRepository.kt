package com.example.domain.repository

import com.example.domain.model.CartDomain

interface CartListRepository {
    suspend fun getCartList():List<CartDomain>
    suspend fun getFinalPrice():Double
    suspend fun deleteItemCart(cartDomain: CartDomain)
}