package com.example.data.repository

import com.example.data.api.ApiService
import com.example.cakes.domain.mappers.OrderRequestMapper
import com.example.cakes.domain.mappers.OrderResponseMapper
import com.example.cakes.domain.model.OrderRequestDomain
import com.example.cakes.domain.model.OrderResponseDomain
import com.example.cakes.domain.repository.OrderRepository

class OrderRepositoryImpl(private val apiService: ApiService): OrderRepository {

    private val orderResponseMapper = OrderResponseMapper()
    private val orderRequestMapper = OrderRequestMapper()
    override suspend fun sendingData(orderRequestDomain: OrderRequestDomain): OrderResponseDomain {
        val response = apiService.postOrder(orderRequestMapper.mapToData(orderRequestDomain))
        if (response.isSuccessful) {
            return orderResponseMapper.mapToDomain(response.body()!!)
        } else
            throw Exception("Failed to post order")
    }
}