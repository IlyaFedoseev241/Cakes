package com.example.cakes.ui.notifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.cakes.api.OrderRequestClass
import com.example.cakes.api.OrderResponseClass
import com.example.cakes.api.ApiService
import com.example.cakes.database.Cart
import com.example.cakes.databinding.FragmentAddressInputBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressInputFragment : Fragment() {
    private var _binding: FragmentAddressInputBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddressInputViewModel

    companion object{
        private var _date:String?=null
        fun newInstance(date:String):AddressInputFragment{
            val args = Bundle().apply {
                getString("DATE",date)
            }
            val fragment = AddressInputFragment()
            fragment.arguments = args
            this._date = date
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(AddressInputViewModel::class.java)
        _binding = FragmentAddressInputBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputAddressBtn.setOnClickListener { callServer() }

    }
    private fun callServer(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val cart:MutableList<String> = mutableListOf()
        viewModel.cartList.observe(viewLifecycleOwner, Observer {
            for(i in it.indices){
                cart.add("Название: ${it[i].cakeName} Вес: ${it[i].cakeWeight} Цена : ${it[i].cakePrice}")
                Log.e("TAG",cart[i])
            }
        })

        val request = OrderRequestClass(userPhone = binding.inputAddressPhone.text.toString(),
            userAddress = binding.inputAddressAddress.text.toString(), userCart = cart, orderDate = _date.toString())
        apiService.order(request).enqueue(object : Callback<OrderResponseClass> {
            override fun onResponse(call: Call<OrderResponseClass>, response: Response<OrderResponseClass>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    Log.e("TAG","token = $token")
                } else {
                    Log.e("TAG","Ошибка")
                }
            }

            override fun onFailure(call: Call<OrderResponseClass>, t: Throwable) {
                Log.e("TAG", "Ошибка сети: ${t.message}")
            }
        })
    }


}