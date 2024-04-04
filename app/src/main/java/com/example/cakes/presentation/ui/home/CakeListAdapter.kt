package com.example.cakes.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakes.Product
import com.example.cakes.R

class CakeListAdapter(private val productItem: List<Product>, private val addToCartClickListener: AddToCartClickListener):RecyclerView.Adapter<CakeListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(
           R.layout.product_item,
           parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return productItem.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productItem[position]
        holder.productNameAd.text = currentItem.productName
        holder.productPriceAd.text = currentItem.productPrice
        Glide.with(holder.itemView.context).load(currentItem.productImage).into(holder.productImageAd)
        holder.addToCart.setOnClickListener {
            addToCartClickListener.addToCartClick(productItem[position])
        }
    }


    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val productNameAd : TextView = itemView.findViewById(R.id.product_name)
        val productPriceAd : TextView = itemView.findViewById(R.id.product_price)
        val productImageAd : ImageView = itemView.findViewById(R.id.product_image)
        val addToCart: Button = itemView.findViewById(R.id.button_add_to_cart)

    }


}