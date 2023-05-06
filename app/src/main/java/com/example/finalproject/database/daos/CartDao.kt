package com.example.finalproject.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalproject.database.entities.Cart
import com.example.finalproject.database.entities.User

@Dao
interface CartDao {

    @Insert
    suspend fun addCart(cart: Cart)

    @Delete
    suspend fun deleteCart(cart: Cart)

    @Query("SELECT * FROM cart_table")
    fun getAllCarts(): List<Cart>

    @Update
    suspend fun updateCart(cart: Cart)
}