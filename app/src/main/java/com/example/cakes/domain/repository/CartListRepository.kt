package com.example.cakes.domain.repository

import com.example.cakes.Cart

interface CartListRepository {
    suspend fun getCartList():List<Cart>
    suspend fun getFinalPrice():Double
}