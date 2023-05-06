package com.example.finalproject.database.repositories

import androidx.lifecycle.LiveData
import com.example.finalproject.database.entities.Cart
import com.example.finalproject.database.entities.User
import com.example.finalproject.database.userDatabase

class userRepo(private val db:userDatabase) {

    //User functions
    fun readAllUsers() = db.userDao().getAllUsers()

    fun getUserById(id:Int) = db.userDao().getUserById(id)

    suspend fun addUser(user:User) = db.userDao().addUser(user)

    suspend fun deleteUser(user: User) = db.userDao().deleteUser(user)

    suspend fun updateUser(user: User) = db.userDao().updateUser(user)

    fun getUserByUserName(name:String) = db.userDao().getUserByName(name)


    //Cart functions
    fun readAllCart() = db.cartDao().getAllCarts()

    suspend fun addCart(cart: Cart) = db.cartDao().addCart(cart)

    suspend fun deleteCart(cart: Cart) = db.cartDao().deleteCart(cart)

    suspend fun updateCart(cart: Cart) = db.cartDao().updateCart(cart)
}