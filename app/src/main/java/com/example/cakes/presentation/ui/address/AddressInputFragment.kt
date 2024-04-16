package com.example.cakes.presentation.ui.address

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.data.api.RetrofitClient
import com.example.cakes.databinding.FragmentAddressInputBinding
import com.example.domain.model.OrderRequestDomain

class AddressInputFragment : Fragment() {
    private var _binding: FragmentAddressInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddressInputViewModel by viewModels {
        AddressInputViewModelFactory(apiService = com.example.data.api.RetrofitClient.apiService)
    }

    companion object {
        private var _date: String? = null
        fun newInstance(date: String): AddressInputFragment {
            val args = Bundle().apply {
                getString("DATE", date)
            }
            val fragment = AddressInputFragment()
            fragment.arguments = args
            _date = date
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inputAddressBtn.setOnClickListener { callServer() }

    }

    private fun callServer() {
        val cart: MutableList<String> = mutableListOf()
        viewModel.cartDataList.observe(viewLifecycleOwner, Observer {
            for (i in it.indices) {
                cart.add("Название: ${it[i].cakeName} Вес: ${it[i].cakeWeight} Цена : ${it[i].cakePrice}")
                Log.e("TAG", cart[i])
            }
        })

        val request = com.example.domain.model.OrderRequestDomain(
            userPhone = binding.inputAddressPhone.text.toString(),
            userAddress = binding.inputAddressAddress.text.toString(),
            userCart = cart,
            orderDate = _date.toString()
        )
        viewModel.postOrder(request)
        viewModel.token.observe(viewLifecycleOwner, Observer {
            Log.e("TAG",it)
        })

    }

}