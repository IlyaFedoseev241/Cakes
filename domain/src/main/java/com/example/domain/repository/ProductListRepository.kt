package com.example.domain.repository

import com.example.domain.model.ProductDomain

interface ProductListRepository {
    suspend fun getProductList():List<ProductDomain>
}