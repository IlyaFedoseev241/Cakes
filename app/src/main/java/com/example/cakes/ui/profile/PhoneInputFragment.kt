package com.example.cakes.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cakes.MainActivity
import com.example.cakes.R
import com.example.cakes.database.DAO
import com.example.cakes.database.MainDatabase
import com.example.cakes.database.RepositoryName
import com.example.cakes.database.User
import com.example.cakes.databinding.FragmentPhoneInputBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

const val USER_SETTINGS = "UserSettings"
const val IS_LOGGED = "isLogged"
class PhoneInputFragment : Fragment() {

    private lateinit var sharedPref:SharedPreferences
    private lateinit var editor:Editor
    private lateinit var phoneInputViewModel: PhoneInputViewModel
    private var _binding: FragmentPhoneInputBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mNumberPhone:String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
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
        val isLogged = sharedPref.getBoolean(IS_LOGGED,false)
       if (isLogged){
           findNavController().navigate(R.id.phone_to_profile)
        }
    }
    override fun onStart() {
        super.onStart()
        mAuth=FirebaseAuth.getInstance()
        mCallback = object :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener{
                    if(it.isSuccessful){
                        Toast.makeText(context,"Добро пожаловать",Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(context,"Ошибка авторизации",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onVerificationFailed(p0: FirebaseException) {
                Log.e("TAG",p0.message.toString())
                Toast.makeText(context,p0.message.toString(),Toast.LENGTH_SHORT).show()
            }
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                callbacks?.inputCode(mNumberPhone,id)
            }

        }
        val admin = User("0","Admin","+79280372951")
        binding.phoneForAdmin.setOnClickListener {
            phoneInputViewModel.insertUser(admin)
            editor.putBoolean(IS_LOGGED,true).apply()
            callbacks?.adminLogin()
        }
        binding.authBtn.setOnClickListener { sendCode() }

        binding.deleteDb.setOnClickListener {
//            phoneInputViewModel.deleteUser(admin)
//            editor.putBoolean(IS_LOGGED,false).apply()
            requireActivity().deleteDatabase("cake.db")
        }

    }

    private fun sendCode(){
        if(binding.phoneEt.text.isEmpty()){
            Toast.makeText(context,"Введите номер телефона", Toast.LENGTH_SHORT).show()
        }else {
            authUser()
        }
    }

    private fun authUser(){
        mNumberPhone = binding.phoneEt.text.toString()
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(mNumberPhone)
            .setTimeout(60,TimeUnit.SECONDS)
            .setActivity(activity as MainActivity)
            .setCallbacks(mCallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun init(){
        sharedPref = requireActivity().getSharedPreferences(USER_SETTINGS,Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        phoneInputViewModel =ViewModelProvider(this).get(PhoneInputViewModel::class.java)
    }
    interface Callbacks{
        fun inputCode(phoneNumber:String,id:String)
        fun adminLogin()
    }
    var callbacks: Callbacks?=null

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