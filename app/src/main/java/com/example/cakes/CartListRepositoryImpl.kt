package com.example.cakes

import com.example.cakes.domain.repository.CartListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartListRepositoryImpl :CartListRepository {
    private val db = MainDatabase.getDB(CakesApplication.applicationContext())
    private val dao = db.getDao()
    override suspend fun getCartList(): List<Cart> {
        return withContext(Dispatchers.IO){
            dao.getAllCart()
        }
    }

    override suspend fun getFinalPrice(): Double {
        return withContext(Dispatchers.IO){
            dao.getTotalPrice()
        }
    }
}