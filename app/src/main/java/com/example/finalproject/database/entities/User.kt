package com.example.finalproject.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var name:String,
    var email:String,
    var phoneNo:String,
    var username:String,
    var password:String
)
