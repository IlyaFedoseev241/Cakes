package com.example.domain.usecase.homeusecase

import com.example.domain.model.ProductDomain
import com.example.domain.repository.ProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProductListUseCase(private val productListRepository: ProductListRepository) {
     suspend fun execute():List<ProductDomain>{
        return withContext(Dispatchers.IO) {
            productListRepository.getProductList()
        }
    }
}
