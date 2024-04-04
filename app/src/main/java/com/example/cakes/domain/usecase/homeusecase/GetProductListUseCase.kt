package com.example.cakes.domain.usecase.homeusecase

import com.example.cakes.Product
import com.example.cakes.domain.repository.ProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductListUseCase(private val productListRepository: ProductListRepository) {
     suspend fun execute():List<Product>{
        return withContext(Dispatchers.IO) {
            productListRepository.getProductList()
        }
    }
}
