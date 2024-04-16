package com.example.data.repository

import com.example.cakes.CakesApplication
import com.example.cakes.domain.mappers.CartMapper
import com.example.data.database.LocalDatabase
import com.example.cakes.domain.model.CartDomain
import com.example.cakes.domain.repository.CartListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartListRepositoryImpl() :CartListRepository {
    private val db = LocalDatabase.getDB(CakesApplication.applicationContext())
    private val dao = db.getDao()
    private val cartMapper = CartMapper()
    override suspend fun getCartList(): List<CartDomain> {
        return withContext(Dispatchers.IO){
            dao.getAllCart().map {cartMapper.mapToDomain(it) }
        }
    }

    override suspend fun getFinalPrice(): Double {
        return withContext(Dispatchers.IO){
            dao.getTotalPrice()
        }
    }

    override suspend fun deleteItemCart(cartDomain: CartDomain) {
        withContext(Dispatchers.IO){
            dao.deleteItemFromCart(cartMapper.mapToData(cartDomain))
        }
    }

}