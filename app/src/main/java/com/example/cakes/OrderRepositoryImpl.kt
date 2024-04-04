package com.example.cakes

import com.example.cakes.api.ApiService
import com.example.cakes.api.RequestOrderData
import com.example.cakes.api.ResponseOrderData
import com.example.cakes.domain.repository.OrderRepository

class OrderRepositoryImpl(private val apiService: ApiService): OrderRepository {
    override suspend fun sendingData(requestOrderData: RequestOrderData): ResponseOrderData {
        val response = apiService.postOrder(requestOrderData)
        if(response.isSuccessful){
            return response.body()!!
        }else
            throw Exception("Failed to post order")
    }
}