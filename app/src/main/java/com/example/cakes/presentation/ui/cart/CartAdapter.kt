package com.example.cakes.presentation.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CartDomain
import com.example.cakes.R

class CartAdapter(private val cartItem:List<com.example.domain.model.CartDomain>, private val deleteFromCartClickListener: DeleteFromCartClickListener):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.cart_item,parent,false)
        return CartViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return cartItem.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItem[position]
        holder.cakeName.text = currentItem.cakeName
        holder.cakeWeight.text = currentItem.cakeWeight
        holder.cakePrice.text = currentItem.cakePrice
        holder.deleteButton.setOnClickListener {
            deleteFromCartClickListener.deleteFromCartClick(currentItem)
        }
    }
    class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cakeName:TextView = itemView.findViewById<TextView>(R.id.item_cake_name)
        val cakeWeight:TextView = itemView.findViewById<TextView>(R.id.item_cake_weight)
        val cakePrice:TextView = itemView.findViewById<TextView>(R.id.item_cake_price)
        val deleteButton:ImageButton = itemView.findViewById<ImageButton>(R.id.item_delete_btn)
    }
}