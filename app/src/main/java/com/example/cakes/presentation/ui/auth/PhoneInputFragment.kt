package com.example.cakes.presentation.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cakes.MainActivity
import com.example.cakes.R
import com.example.data.database.User
import com.example.cakes.databinding.FragmentPhoneInputBinding

const val USER_SETTINGS = "UserSettings"
const val IS_LOGGED = "isLogged"

class PhoneInputFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: Editor
    private lateinit var phoneInputViewModel: PhoneInputViewModel
    private var _binding: FragmentPhoneInputBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()

        _binding = FragmentPhoneInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isLogged = sharedPref.getBoolean(IS_LOGGED, false)
        if (isLogged) {
            findNavController().navigate(R.id.phone_to_profile)
        }
    }

    override fun onStart() {
        super.onStart()
        phoneInputViewModel.firebasePhoneProvider(callbacks!!, requireContext())
        val admin = com.example.data.database.User("0", "Admin", "+79280372951")
        binding.phoneForAdmin.setOnClickListener {
            phoneInputViewModel.insertUser(admin)
            editor.putBoolean(IS_LOGGED, true).apply()
            callbacks?.adminLogin()
        }
        binding.authBtn.setOnClickListener {
            phoneInputViewModel.sendCode(
                binding.phoneEt.text.toString(),
                activity as MainActivity
            )
        }
        binding.deleteDb.setOnClickListener {
            requireActivity().deleteDatabase("cake.db")
        }

    }

    private fun init() {
        sharedPref = requireActivity().getSharedPreferences(USER_SETTINGS, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        phoneInputViewModel = ViewModelProvider(this).get(PhoneInputViewModel::class.java)
    }

    interface Callbacks {
        fun inputCode(phoneNumber: String, id: String)
        fun adminLogin()
    }

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onDetach() {
        callbacks = null
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}