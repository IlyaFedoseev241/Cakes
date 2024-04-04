package com.example.cakes.domain.model

interface OrderRequest{
    val userPhone: String
    val userAddress: String
    val userCart: List<String>
    val orderDate: String
}

