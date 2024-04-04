package com.example.cakes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    @Query("SELECT *  FROM user")
    fun loadUser():User
    @Update
    suspend fun updateUser(user: User)
    @Insert
    fun insertUser(user: User)
    @Delete
    fun deleteUser(user: User)

    @Query("SELECT *  FROM cart")
    fun getAllCart():MutableList<Cart>
    @Insert
    fun insertItemToCart(cart: Cart)
    @Delete
    fun deleteItemFromCart(cart: Cart)
    @Query("SELECT SUM(CAST(cakePrice AS REAL))  FROM cart")
    fun getTotalPrice():Double
}
