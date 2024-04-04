package com.example.cakes.domain.usecase.addressusecase

import com.example.cakes.api.RequestOrderData
import com.example.cakes.api.ResponseOrderData
import com.example.cakes.domain.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostOrderUseCase(private val orderRepository: OrderRepository) {
    suspend fun execute(requestOrderData: RequestOrderData):ResponseOrderData{
        return withContext(Dispatchers.IO){
            orderRepository.sendingData(requestOrderData)
        }
    }
}