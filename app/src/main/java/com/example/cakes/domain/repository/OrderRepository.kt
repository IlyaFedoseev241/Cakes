package com.example.cakes.domain.repository

import com.example.cakes.api.RequestOrderData
import com.example.cakes.api.ResponseOrderData

interface OrderRepository {
    suspend fun sendingData(requestOrderData: RequestOrderData):ResponseOrderData
}