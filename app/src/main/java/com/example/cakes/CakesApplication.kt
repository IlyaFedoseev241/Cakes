package com.example.cakes

import android.app.Application
import android.content.Context

class CakesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseRepository.newInstance()
    }
    init {
        instance=this
    }
    companion object{
        private var instance:CakesApplication? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}