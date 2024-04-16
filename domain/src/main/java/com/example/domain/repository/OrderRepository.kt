package com.example.domain.repository

import com.example.domain.model.OrderRequestDomain
import com.example.domain.model.OrderResponseDomain

interface OrderRepository {
    suspend fun sendingData(orderRequestDomain: OrderRequestDomain): OrderResponseDomain
}