package com.example.cakes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id:String,
    @ColumnInfo(name = "userName")
    var userName:String,
    @ColumnInfo(name = "userPhone")
    val userPhoneDB:String,
)
