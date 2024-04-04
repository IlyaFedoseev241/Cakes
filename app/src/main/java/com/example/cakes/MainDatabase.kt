package com.example.cakes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =
    [User::class,
    Cart::class],
    version = 1)
abstract class MainDatabase:RoomDatabase() {
    abstract fun getDao(): DAO
    companion object{
        fun getDB(context: Context): MainDatabase {
            return Room.databaseBuilder(context.applicationContext, MainDatabase::class.java,"cake.db").build()
        }
    }
}