package com.example.domain.mappers

import com.example.data.api.OrderRequestData
import com.example.domain.model.OrderRequestDomain

class OrderRequestMapper : EntityMapper<OrderRequestDomain, com.example.data.api.OrderRequestData> {
    override fun mapToDomain(data: com.example.data.api.OrderRequestData): OrderRequestDomain {
        return OrderRequestDomain(
            userPhone = data.userPhone,
            userAddress = data.userAddress,
            userCart = data.userCart,
            orderDate = data.orderDate
        )
    }

    override fun mapToData(domain: OrderRequestDomain): com.example.data.api.OrderRequestData {
        return com.example.data.api.OrderRequestData(
            userPhone = domain.userPhone,
            userAddress = domain.userAddress,
            userCart = domain.userCart,
            orderDate = domain.orderDate
        )
    }
}