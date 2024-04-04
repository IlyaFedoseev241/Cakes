package com.example.cakes.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cakes.CartAdapter
import com.example.cakes.R
import com.example.cakes.databinding.FragmentNotificationsBinding

class CartFragment : Fragment() {

    private lateinit var cartViewModel:CartViewModel
    private var layoutManager: RecyclerView.LayoutManager?=null

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel=ViewModelProvider(this).get(CartViewModel::class.java)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        _binding!!.cartRecView.hasFixedSize()
        layoutManager=LinearLayoutManager(activity)
        _binding!!.cartRecView.layoutManager=layoutManager

        return binding.root
    }
    private var cartAdapter:CartAdapter = CartAdapter(emptyList())
    private var finishPrice:Double = 0.0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recView()
        getFinishSum()
        binding.cartNextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_cart_to_navigation_date)
        }
    }
    private fun recView(){
        cartViewModel.cartList.observe(viewLifecycleOwner, Observer {
            cartAdapter = CartAdapter(it)
            binding.cartRecView.adapter=cartAdapter
        })
        cartViewModel.getAllCart()
    }
    private fun getFinishSum(){
        cartViewModel.finishPrice.observe(viewLifecycleOwner, Observer {
            finishPrice = it
            binding.cartTotalSumTv.text = finishPrice.toString()
        })
        cartViewModel.getTotalPrice()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}