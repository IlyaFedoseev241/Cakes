package com.example.cakes.domain.usecase.cartusecase

import com.example.cakes.Cart
import com.example.cakes.domain.repository.CartListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCartListUseCase(private val cartListRepository: CartListRepository) {
    suspend fun execute():List<Cart>{
        return withContext(Dispatchers.IO){
            cartListRepository.getCartList()
        }
    }
}