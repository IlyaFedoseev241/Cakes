package com.example.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.cakes.CakesApplication
import com.example.cakes.domain.mappers.CartMapper
import com.example.data.database.LocalDatabase
import com.example.data.database.User
import com.example.data.model.CartData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository {
    var user:MutableLiveData<User> = MutableLiveData()
    var cartDataList:MutableLiveData<List<CartData>> = MutableLiveData()
    var finishPrice: MutableLiveData<Double> = MutableLiveData()
    private val cartMapper = CartMapper()
    companion object{
        private var INSTANCE: DatabaseRepository?=null
        fun newInstance(){
            if(INSTANCE ==null){
                INSTANCE = DatabaseRepository()
            }
        }
        fun get(): DatabaseRepository {
            return INSTANCE ?:
            throw IllegalStateException("Репозиторий RepositoryName не инициализирован")
        }
    }
    private val db = LocalDatabase.getDB(CakesApplication.applicationContext())
    private val dao = db.getDao()

    suspend fun insertUser(user: User){
        withContext(Dispatchers.IO){
            dao.insertUser(user)
        }
    }
    suspend fun deleteUser(user: User){
        withContext(Dispatchers.IO){
            dao.deleteUser(user)
        }
    }
    suspend fun loadUser(): User {
        return withContext(Dispatchers.IO){
            dao.loadUser()
        }
    }
    suspend fun updateUser(user: User){
        withContext(Dispatchers.IO){
            dao.updateUser(user)
        }
    }

    suspend fun insertItemToCart(cartData: CartData){
        withContext(Dispatchers.IO){
            dao.insertItemToCart(cartData)
            cartDataList.postValue(dao.getAllCart())
        }
    }
    suspend fun deleteItemFromCart(cartData: CartData){
        withContext(Dispatchers.IO){
            dao.deleteItemFromCart(cartData)
            cartDataList.postValue(dao.getAllCart())
            finishPrice.postValue(dao.getTotalPrice())
        }
    }
    suspend fun getAllCart(){
         withContext(Dispatchers.IO){
            cartDataList.postValue(dao.getAllCart())
             finishPrice.postValue(dao.getTotalPrice())
        }
    }
    suspend fun getTotalPrice():Double{
        return withContext(Dispatchers.IO){
            dao.getTotalPrice()
        }
    }
}