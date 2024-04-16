package com.example.cakes.presentation.ui.cart

import com.example.domain.model.CartDomain

interface DeleteFromCartClickListener {
    fun deleteFromCartClick(cartDomain: com.example.domain.model.CartDomain)
}