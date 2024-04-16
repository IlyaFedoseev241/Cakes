package com.example.data.api

data class OrderRequestData(
     val userPhone: String,
     val userAddress: String,
     val userCart: List<String>,
     val orderDate: String
)

