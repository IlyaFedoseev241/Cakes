package com.example.cakes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cakes.Data.Products
import com.example.cakes.MyAdapter
import com.example.cakes.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var productsRef: DatabaseReference?=null
    private var layoutManager: RecyclerView.LayoutManager?=null
    private lateinit var productArrayList:ArrayList<Products>
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        _binding!!.productsRecyclerview.hasFixedSize()
        layoutManager = LinearLayoutManager(activity)
        _binding!!.productsRecyclerview.layoutManager=layoutManager
        productArrayList= arrayListOf<Products>()
        recView()
        return root

    }

     private fun recView() {

        productsRef = FirebaseDatabase.getInstance().reference.child("Products")
        productsRef!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(i in snapshot.children){
                        val product=i.getValue(Products::class.java)
                        productArrayList.add(product!!)
                    }
                    _binding!!.productsRecyclerview.adapter=MyAdapter(productArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("DatabaseError", error.message)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}