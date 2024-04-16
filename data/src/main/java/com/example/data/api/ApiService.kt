package com.example.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @POST("order")
    suspend fun postOrder(@Body request: OrderRequestData): Response<OrderResponseData>
}