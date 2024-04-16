package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

import androidx.room.Query
import androidx.room.Update
import com.example.data.model.CartData

@Dao
interface DAO {
    @Query("SELECT *  FROM user")
    fun loadUser(): User
    @Update
    suspend fun updateUser(user: User)
    @Insert
    fun insertUser(user: User)
    @Delete
    fun deleteUser(user: User)

    @Query("SELECT *  FROM cart")
    fun getAllCart():MutableList<CartData>
    @Insert
    fun insertItemToCart(cartData: CartData)
    @Delete
    fun deleteItemFromCart(cartData: CartData)
    @Query("SELECT SUM(CAST(cakePrice AS REAL))  FROM cart")
    fun getTotalPrice():Double
}
