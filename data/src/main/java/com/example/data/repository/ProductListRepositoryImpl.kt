package com.example.data.repository

import com.example.cakes.domain.model.ProductDomain
import com.example.cakes.domain.repository.ProductListRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductListRepositoryImpl(): ProductListRepository {
    override suspend fun getProductList(): List<ProductDomain> {
        return withContext(Dispatchers.IO){
            val productsRef = FirebaseDatabase.getInstance().reference.child("Products")
            val snapshot = productsRef.get().await()
            snapshot.children.mapNotNull {it.getValue(ProductDomain::class.java)}
        }
    }
}