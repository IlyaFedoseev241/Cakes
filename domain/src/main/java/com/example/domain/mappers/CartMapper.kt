package com.example.domain.mappers

import com.example.data.model.CartData
import com.example.domain.model.CartDomain

class CartMapper: EntityMapper<CartDomain, com.example.data.model.CartData> {
    override fun mapToDomain(data: com.example.data.model.CartData): CartDomain {
        return CartDomain(
            id = data.id,
            productID = data.productID,
            cakeName = data.cakeName,
            cakePrice = data.cakePrice,
            cakeWeight = data.cakeWeight
        )
    }
    override fun mapToData(domain: CartDomain): com.example.data.model.CartData {
        return com.example.data.model.CartData(
            id = domain.id,
            productID = domain.productID,
            cakeName = domain.cakeName,
            cakePrice = domain.cakePrice,
            cakeWeight = domain.cakeWeight
        )
    }

}