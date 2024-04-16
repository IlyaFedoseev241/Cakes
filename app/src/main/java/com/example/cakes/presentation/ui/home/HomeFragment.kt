package com.example.cakes.presentation.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.CartData
import com.example.data.repository.DatabaseRepository
import com.example.data.model.ProductData
import com.example.domain.model.ProductDomain
import com.example.cakes.R
import com.example.cakes.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), AddToCartClickListener {

    private var _binding: FragmentHomeBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var productDataArrayList: List<com.example.data.model.ProductData>
    private lateinit var  homeViewModel:HomeViewModel
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        _binding!!.productsRecyclerview.hasFixedSize()
        layoutManager = LinearLayoutManager(activity)
        _binding!!.productsRecyclerview.layoutManager = layoutManager
        productDataArrayList = listOf<com.example.data.model.ProductData>()


        return root

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.product.observe(viewLifecycleOwner, Observer {
            _binding!!.productsRecyclerview.adapter = CakeListAdapter(it, this@HomeFragment)
            Log.e("TAG","Массив =  $it")
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addToCartClick(productDomain: com.example.domain.model.ProductDomain) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(true)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.input_order, null)
        builder.setView(dialogView)
        //Initialization
        val cakeName = dialogView.findViewById<TextView>(R.id.input_cake_name)
        val cakePrice = dialogView.findViewById<TextView>(R.id.input_total_price)
        val cakeWeight = dialogView.findViewById<TextView>(R.id.input_weight_cake)
        val plusButton = dialogView.findViewById<ImageView>(R.id.input_plus)
        val minusButton = dialogView.findViewById<ImageView>(R.id.input_minus)
        val buttonAddToCart = dialogView.findViewById<Button>(R.id.input_button_add_to_cart)
        //Summa
        val cakePriceValue = productDomain.productPrice.toString().toInt()
        val cakeWeightValue = cakeWeight.text.toString().toInt()
        val result = cakePriceValue * cakeWeightValue
        cakePrice.text = result.toString()

        plusButton.setOnClickListener {
            val kol = cakeWeight.text.toString().toInt() + 1
            cakeWeight.text = kol.toString()
            val newPrice = cakePriceValue * kol
            cakePrice.text = newPrice.toString()
        }
        minusButton.setOnClickListener {
            val kol = cakeWeight.text.toString().toInt() - 1
            cakeWeight.text = kol.toString()
            val newPrice = cakePriceValue * kol
            cakePrice.text = newPrice.toString()
        }
        cakeName.text = productDomain.productName

        val alert = builder.create()
        alert.show()
        buttonAddToCart.setOnClickListener {
            val id = productDomain.productID.toString()
            val price = cakePrice.text.toString()
            val name = cakeName.text.toString()
            val weight = cakeWeight.text.toString()
            val cartData = com.example.data.model.CartData(0, id, name, price, weight)
            CoroutineScope(Dispatchers.IO).launch {
                com.example.data.repository.DatabaseRepository.get().insertItemToCart(cartData)
            }
            alert.dismiss()
        }
    }

}