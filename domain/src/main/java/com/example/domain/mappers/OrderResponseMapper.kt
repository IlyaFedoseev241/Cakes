package com.example.domain.mappers

import com.example.data.api.OrderResponseData
import com.example.domain.model.OrderResponseDomain

class OrderResponseMapper :
    EntityMapper<OrderResponseDomain, com.example.data.api.OrderResponseData> {
    override fun mapToDomain(data: com.example.data.api.OrderResponseData): OrderResponseDomain {
        return OrderResponseDomain(data.token)
    }

    override fun mapToData(domain: OrderResponseDomain): com.example.data.api.OrderResponseData {
        return com.example.data.api.OrderResponseData(domain.token)
    }
}