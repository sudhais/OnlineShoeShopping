package com.example.finalproject.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalproject.database.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user:User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllUsers():List<User>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id:Int):User

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table WHERE username = :name")
    fun getUserByName(name:String): User

}