package com.example.cakes.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cakes.R
import com.example.cakes.database.MainDatabase
import com.example.cakes.database.RepositoryName
import com.example.cakes.databinding.FragmentCodeConfirmBinding
import com.example.cakes.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.editUserName.setOnClickListener{
            Log.e("Tag","TAP")
            editName()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.action_navigation_your_profile_to_navigation_menu)
        }
        profileViewModel.user.observe(requireActivity(), Observer {
            binding.profilePhoneTv.text = it.userPhoneDB
            binding.profileUserName.text=it.userName
        })

    }
    private fun editName(){
        binding.profileUserName.visibility=View.INVISIBLE
        binding.editUserName.visibility=View.INVISIBLE
        binding.userNameEt.visibility=View.VISIBLE
        binding.userNameEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrBlank()){
                    binding.profileUserName.text = "Ваше имя"
                }else{
                    binding.profileUserName.text=s
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.userNameEt.setOnKeyListener { _, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) {
                binding.userNameEt.visibility=View.INVISIBLE
                binding.profileUserName.visibility=View.VISIBLE
                binding.editUserName.visibility=View.VISIBLE
                val currentUser = profileViewModel.user.value
                currentUser?.let {
                    it.userName = binding.profileUserName.text.toString()
                    profileViewModel.updateUser(it)
                }
                true
            } else {
                false
            }
        }

    }


}