package com.example.cakes.presentation.ui.date

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cakes.databinding.FragmentDateBinding
import java.text.DateFormat
import java.util.Calendar

class DateFragment : Fragment() {

    private lateinit var viewModel: DateViewModel
    private var _binding: FragmentDateBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[DateViewModel::class.java]
        _binding = FragmentDateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateNextBtn.setOnClickListener {
            if (radioGroupCheck()){
                getDate()
                callbacks?.inputAddress(getDate())
            }
        }


    }
    private fun getDate():String{
        val year = binding.datePicker.year
        val month = binding.datePicker.month
        val day = binding.datePicker.dayOfMonth
        val calendar = Calendar.getInstance()
        calendar.set(year,month,day)
        return formattedDate(calendar)

    }
    private fun formattedDate(calendar: Calendar):String{
        val date = calendar.time
        val dateFormat = DateFormat.getDateInstance()
        return dateFormat.format(date)
    }
    private fun radioGroupCheck():Boolean{
        return binding.deliveryCourier.isChecked
    }
    private var callbacks: Callbacks?=null
    interface Callbacks{
        fun inputAddress(date:String)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks=context as Callbacks
    }

    override fun onDetach() {
        callbacks=null
        super.onDetach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}