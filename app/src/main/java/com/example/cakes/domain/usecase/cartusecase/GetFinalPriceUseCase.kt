package com.example.cakes.domain.usecase.cartusecase

import com.example.cakes.domain.repository.CartListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFinalPriceUseCase(private val cartListRepository: CartListRepository) {
    suspend fun execute():Double{
        return withContext(Dispatchers.IO){
            cartListRepository.getFinalPrice()
        }
    }
}