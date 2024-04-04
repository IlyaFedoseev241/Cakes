package com.example.cakes.api

import com.example.cakes.domain.model.OrderRequest

data class RequestOrderData(
    override val userPhone: String,
    override val userAddress: String,
    override val userCart: List<String>,
    override val orderDate: String
):OrderRequest

