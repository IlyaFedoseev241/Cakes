package com.example.cakes.api

import com.example.cakes.domain.model.OrderResponse

data class ResponseOrderData(
    override val token: String
):OrderResponse