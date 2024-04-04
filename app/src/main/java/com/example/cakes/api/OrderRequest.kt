package com.example.cakes.api

import com.example.cakes.database.Cart

data class OrderRequestClass(
    val userPhone:String,
    val userAddress:String,
    val userCart:MutableList<String>,
    val orderDate:String
)
data class OrderResponseClass(
    val token:String
)
