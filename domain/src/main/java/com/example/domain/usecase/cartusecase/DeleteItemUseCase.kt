package com.example.domain.usecase.cartusecase

import com.example.domain.model.CartDomain
import com.example.domain.repository.CartListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteItemUseCase(private val cartListRepository: CartListRepository) {
    suspend fun execute(cartDomain: CartDomain){
        withContext(Dispatchers.IO){
            cartListRepository.deleteItemCart(cartDomain)
        }
    }
}