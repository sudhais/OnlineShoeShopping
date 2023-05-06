package com.example.finalproject.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val shoeName:String,
    val shoeImage:Int,
    val shoePrice:Double,
    val quanity:Int,
    val totPrice:Double

)
