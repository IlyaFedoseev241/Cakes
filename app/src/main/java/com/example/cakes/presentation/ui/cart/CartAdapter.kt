package com.example.cakes.presentation.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cakes.R
import com.example.cakes.Cart
import com.example.cakes.DatabaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(private val cartItem:List<Cart>):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
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
            CoroutineScope(Dispatchers.IO).launch{
                DatabaseRepository.get().deleteItemFromCart(currentItem)
            }
        }
    }
    class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cakeName:TextView = itemView.findViewById<TextView>(R.id.item_cake_name)
        val cakeWeight:TextView = itemView.findViewById<TextView>(R.id.item_cake_weight)
        val cakePrice:TextView = itemView.findViewById<TextView>(R.id.item_cake_price)
        val deleteButton:ImageButton = itemView.findViewById<ImageButton>(R.id.item_delete_btn)
    }
}