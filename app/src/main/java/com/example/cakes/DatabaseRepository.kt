package com.example.cakes

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository {
    var user:MutableLiveData<User> = MutableLiveData()
    var cartList:MutableLiveData<List<Cart>> = MutableLiveData()
    var finishPrice: MutableLiveData<Double> = MutableLiveData()
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
    private val db = MainDatabase.getDB(CakesApplication.applicationContext())
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

    suspend fun insertItemToCart(cart: Cart){
        withContext(Dispatchers.IO){
            dao.insertItemToCart(cart)
            cartList.postValue(dao.getAllCart())
        }
    }
    suspend fun deleteItemFromCart(cart: Cart){
        withContext(Dispatchers.IO){
            dao.deleteItemFromCart(cart)
            cartList.postValue(dao.getAllCart())
            finishPrice.postValue(dao.getTotalPrice())
        }
    }
    suspend fun getAllCart(){
         withContext(Dispatchers.IO){
            cartList.postValue(dao.getAllCart())
             finishPrice.postValue(dao.getTotalPrice())
        }
    }
    suspend fun getTotalPrice():Double{
        return withContext(Dispatchers.IO){
            dao.getTotalPrice()
        }
    }
}