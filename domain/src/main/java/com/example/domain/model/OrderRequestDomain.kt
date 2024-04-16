package com.example.domain.model

data class OrderRequestDomain(
    val userPhone: String,
    val userAddress: String,
    val userCart: List<String>,
    val orderDate: String
)

