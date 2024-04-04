package com.example.cakes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo("productID")
    val productID:String,
    @ColumnInfo("cakeName")
    val cakeName:String,
    @ColumnInfo("cakePrice")
    val cakePrice:String,
    @ColumnInfo("cakeWeight")
    val cakeWeight:String
)