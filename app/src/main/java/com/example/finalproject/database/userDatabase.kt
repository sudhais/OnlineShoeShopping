package com.example.finalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalproject.database.daos.CartDao
import com.example.finalproject.database.daos.UserDao
import com.example.finalproject.database.entities.Cart
import com.example.finalproject.database.entities.User

@Database(entities = [User::class,Cart::class], version = 1)
abstract class userDatabase: RoomDatabase() {

    abstract fun userDao():UserDao
    abstract fun cartDao():CartDao

    companion object{
        @Volatile
        private var INSTANCE:userDatabase? = null

        fun getInstance(context: Context):userDatabase{

            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    userDatabase::class.java,
                    "user_db"
                ).build().also{
                    INSTANCE= it
                }
            }
        }
    }


}