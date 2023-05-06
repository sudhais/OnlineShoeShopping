package com.example.finalproject.database.repositories

import androidx.lifecycle.LiveData
import com.example.finalproject.database.entities.Cart
import com.example.finalproject.database.entities.User
import com.example.finalproject.database.userDatabase

class userRepo(private val db:userDatabase) {

    //User functions
    // read all user details
    fun readAllUsers() = db.userDao().getAllUsers()

    //get the user details from given user id
    fun getUserById(id:Int) = db.userDao().getUserById(id)

    //add the user
    suspend fun addUser(user:User) = db.userDao().addUser(user)

    //delete the user
    suspend fun deleteUser(user: User) = db.userDao().deleteUser(user)

    //update the user
    suspend fun updateUser(user: User) = db.userDao().updateUser(user)

    //get user detail from given user name
    fun getUserByUserName(name:String) = db.userDao().getUserByName(name)


    //Cart functions
    //read all cart details
    fun readAllCart() = db.cartDao().getAllCarts()

    //add cart details
    suspend fun addCart(cart: Cart) = db.cartDao().addCart(cart)

    //delete cart details
    suspend fun deleteCart(cart: Cart) = db.cartDao().deleteCart(cart)

    //update cart details
    suspend fun updateCart(cart: Cart) = db.cartDao().updateCart(cart)
}