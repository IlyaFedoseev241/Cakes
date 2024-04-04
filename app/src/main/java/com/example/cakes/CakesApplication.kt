package com.example.cakes

import android.app.Application
import android.content.Context
import com.example.cakes.database.RepositoryName

class CakesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RepositoryName.newInstance()
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