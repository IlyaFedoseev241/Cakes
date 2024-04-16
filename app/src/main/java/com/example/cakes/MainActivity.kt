package com.example.cakes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cakes.R
import com.example.cakes.databinding.ActivityMainBinding
import com.example.cakes.presentation.ui.address.AddressInputFragment
import com.example.cakes.presentation.ui.date.DateFragment
import com.example.cakes.presentation.ui.auth.CodeConfirmFragment
import com.example.cakes.presentation.ui.auth.IS_LOGGED
import com.example.cakes.presentation.ui.auth.PhoneInputFragment
import com.example.cakes.presentation.ui.auth.USER_SETTINGS

class MainActivity : AppCompatActivity() , PhoneInputFragment.Callbacks, CodeConfirmFragment.Callbacks,
    DateFragment.Callbacks {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(USER_SETTINGS,Context.MODE_PRIVATE)
        val isLogged = sharedPreferences.getBoolean(IS_LOGGED,false)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_menu, R.id.navigation_phone, R.id.navigation_cart
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun inputCode(phoneNumber:String, id:String){
        val codeFragment = CodeConfirmFragment.newInstance(phoneNumber,id)
        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.switchToCode,codeFragment.arguments)
    }

    override fun adminLogin() {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.phone_to_profile)
    }
    override fun showProfile(){
        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.action_to_navigation_profile)
    }

    override fun inputAddress(date: String) {
        val inputFragment = AddressInputFragment.newInstance(date)
        findNavController(R.id.nav_host_fragment_activity_main)
            .navigate(R.id.action_navigation_date_to_navigation_address,inputFragment.arguments)
    }

}