package com.example.cakes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cakes.Data.Products
import com.example.cakes.database.Cart
import com.example.cakes.database.RepositoryName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyAdapter(private val productItem: ArrayList<Products>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(R.layout.product_item,
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
            val builder= AlertDialog.Builder(holder.itemView.context)
            builder.setCancelable(true)
            val dialogView=LayoutInflater.from(holder.itemView.context).inflate(R.layout.input_order,null)
            builder.setView(dialogView)
            //Initialization
            val cakeName = dialogView.findViewById<TextView>(R.id.input_cake_name)
            val cakePrice = dialogView.findViewById<TextView>(R.id.input_total_price)
            val cakeWeight = dialogView.findViewById<TextView>(R.id.input_weight_cake)
            val plusButton = dialogView.findViewById<ImageView>(R.id.input_plus)
            val minusButton = dialogView.findViewById<ImageView>(R.id.input_minus)
            val buttonAddToCart = dialogView.findViewById<Button>(R.id.input_button_add_to_cart)
            //Summa
            val cakePriceValue = holder.productPriceAd.text.toString().toInt()
            val cakeWeightValue =cakeWeight.text.toString().toInt()
            val result =cakePriceValue*cakeWeightValue
            cakePrice.text = result.toString()

            plusButton.setOnClickListener {
                val kol = cakeWeight.text.toString().toInt()+1
                cakeWeight.text = kol.toString()
                val newPrice = cakePriceValue*kol
                cakePrice.text = newPrice.toString()
            }
            minusButton.setOnClickListener{
                val kol = cakeWeight.text.toString().toInt()-1
                cakeWeight.text = kol.toString()
                val newPrice = cakePriceValue*kol
                cakePrice.text=newPrice.toString()
            }
            cakeName.text = holder.productNameAd.text

            val alert=builder.create()
            alert.show()
            buttonAddToCart.setOnClickListener {
                val id = currentItem.productID.toString()
                val price = cakePrice.text.toString()
                val name = cakeName.text.toString()
                val weight = cakeWeight.text.toString()
                val cart = Cart(0,id,name,price,weight)
                CoroutineScope(Dispatchers.IO).launch{
                    RepositoryName.get().insertItemToCart(cart)
                }
                alert.dismiss()
            }
        }
    }


    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val productNameAd : TextView = itemView.findViewById(R.id.product_name)
        val productPriceAd : TextView = itemView.findViewById(R.id.product_price)
        val productImageAd : ImageView = itemView.findViewById(R.id.product_image)
        val addToCart: Button = itemView.findViewById(R.id.button_add_to_cart)

    }


}