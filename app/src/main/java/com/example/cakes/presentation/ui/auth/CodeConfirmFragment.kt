package com.example.cakes.presentation.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.data.database.User
import com.example.cakes.databinding.FragmentCodeConfirmBinding

class CodeConfirmFragment() : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var codeConfirmViewModel: CodeConfirmViewModel
    private var _binding: FragmentCodeConfirmBinding? = null
    private val binding get() = _binding!!

    companion object{
        private var phoneNumber:String?=null
        private var _id:String?=null
        fun newInstance(phoneNumber:String,id:String): CodeConfirmFragment {
            val args = Bundle().apply {
                getString("NUMBER",phoneNumber)
                getString("ID",id)
            }
            val fragment = CodeConfirmFragment()
            fragment.arguments = args
            _id = id
            Companion.phoneNumber =phoneNumber
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPref = requireActivity().getSharedPreferences(USER_SETTINGS, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        _binding = FragmentCodeConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        codeConfirmViewModel = ViewModelProvider(this)[CodeConfirmViewModel::class.java]
    }
    override fun onStart() {
        super.onStart()
        binding.checkCode.setOnClickListener {
            codeConfirmViewModel.enterCode(_id!!, binding.inputCode.text.toString(),callbacks!!)
            val user = com.example.data.database.User(_id!!, "Не указано", phoneNumber!!)
            codeConfirmViewModel.insertUser(user)
            editor.putBoolean(IS_LOGGED, true).apply()
        }

    }

    interface Callbacks{
        fun showProfile()
    }
    private var callbacks: Callbacks?=null

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

