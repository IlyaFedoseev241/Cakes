package com.example.cakes.ui.profile

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.cakes.databinding.FragmentCodeConfirmBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class CodeConfirmFragment() : Fragment() {

    private lateinit var mAuth:FirebaseAuth
    private var _binding: FragmentCodeConfirmBinding? = null
    private val binding get() = _binding!!

    companion object{
        private var phoneNumber:String?=null
        private var _id:String?=null
        fun newInstance(phoneNumber:String,id:String): CodeConfirmFragment{
            val args = Bundle().apply {
                getString("NUMBER",phoneNumber)
                getString("ID",id)
            }
            val fragment = CodeConfirmFragment()
            fragment.arguments = args
            this._id = id
            this.phoneNumber=phoneNumber
            return fragment
        }
    }
    override fun onStart() {
        super.onStart()
        binding.checkCode.setOnClickListener {
            enterCode()
        }

    }
    private fun enterCode() {
        val code = binding.inputCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(_id.toString(), code)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Добро пожаловать", Toast.LENGTH_SHORT).show()
                callbacks?.showProfile()
            } else {
                Toast.makeText(context, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        _binding = FragmentCodeConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    interface Callbacks{
        fun showProfile()
    }
    var callbacks:Callbacks?=null

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

