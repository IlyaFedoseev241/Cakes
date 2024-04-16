package com.example.domain.usecase.cartusecase

import com.example.domain.model.CartDomain
import com.example.domain.repository.CartListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCartListUseCase(private val cartListRepository: CartListRepository) {
    suspend fun execute():List<CartDomain>{
        return withContext(Dispatchers.IO){
            cartListRepository.getCartList()
        }
    }
}