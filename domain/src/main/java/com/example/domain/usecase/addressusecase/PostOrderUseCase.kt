package com.example.domain.usecase.addressusecase

import com.example.domain.model.OrderRequestDomain
import com.example.domain.model.OrderResponseDomain
import com.example.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostOrderUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(orderRequestDomain: OrderRequestDomain): OrderResponseDomain {
        return withContext(Dispatchers.IO){
            orderRepository.sendingData(orderRequestDomain)
        }
    }
}