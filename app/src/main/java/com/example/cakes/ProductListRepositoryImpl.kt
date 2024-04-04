package com.example.cakes

import com.example.cakes.domain.repository.ProductListRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProductListRepositoryImpl(): ProductListRepository {
    override suspend fun getProductList(): List<Product> {
        return withContext(Dispatchers.IO){
            val productsRef = FirebaseDatabase.getInstance().reference.child("Products")
            val snapshot = productsRef.get().await()
            snapshot.children.mapNotNull {it.getValue(Product::class.java)}
        }
    }

}