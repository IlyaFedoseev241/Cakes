package com.example.cakes.domain.repository

import com.example.cakes.Product

interface ProductListRepository {
    suspend fun getProductList():List<Product>
}