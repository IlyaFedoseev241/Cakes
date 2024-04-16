package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.CartData

@Database(entities =
    [User::class,
    CartData::class],
    version = 1)
abstract class LocalDatabase:RoomDatabase() {
    abstract fun getDao(): DAO
    companion object{
        fun getDB(context: Context): LocalDatabase {
            return Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java,"cake.db").build()
        }
    }
}