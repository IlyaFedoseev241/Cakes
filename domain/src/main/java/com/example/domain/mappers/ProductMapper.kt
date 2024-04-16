package com.example.domain.mappers

import com.example.data.model.ProductData
import com.example.domain.model.ProductDomain

class ProductMapper : EntityMapper<ProductDomain, com.example.data.model.ProductData> {
    override fun mapToDomain(data: com.example.data.model.ProductData): ProductDomain {
        return ProductDomain(data.productID, data.productName, data.productPrice, data.productImage)
    }

    override fun mapToData(domain: ProductDomain): com.example.data.model.ProductData {
        return com.example.data.model.ProductData(
            domain.productID,
            domain.productName,
            domain.productPrice,
            domain.productImage
        )
    }
}