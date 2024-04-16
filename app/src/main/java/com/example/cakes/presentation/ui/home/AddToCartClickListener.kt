package com.example.cakes.presentation.ui.home

import com.example.domain.model.ProductDomain

interface AddToCartClickListener {
    fun addToCartClick(productDomain: com.example.domain.model.ProductDomain)
}