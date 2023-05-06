package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.cartAdapter
import com.example.finalproject.database.entities.Cart
import com.example.finalproject.database.repositories.userRepo
import com.example.finalproject.database.userDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartPage : AppCompatActivity() {
    lateinit var newRecyclerView: RecyclerView
    lateinit var newList: List<Cart>
    lateinit var repository: userRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_page)

        val btnHome = findViewById<ImageView>(R.id.hh)
        val totalPrice = findViewById<TextView>(R.id.totPrice)

        repository = userRepo(userDatabase.getInstance(this))
        newRecyclerView = findViewById(R.id.CartRecycle)
        val ui = this
        val adapter = cartAdapter()
        newRecyclerView.adapter = adapter
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        //newRecyclerView.setHasFixedSize(true)

        //read all cart details from cart_table
        CoroutineScope(Dispatchers.IO).launch {
            var totPrice:Double = 0.0
            val data = repository.readAllCart()
            Log.e("1234", "${data}")
            adapter.setData(data,ui)

            //calculate the total price from getting all cart shoe total price
            for(i in 0 until data.size){
                totPrice += data[i].totPrice
            }
            //set the total price
            totalPrice.setText(totPrice.toString())
            Log.e("1122333", "${totalPrice.text}")
        }

        //redirect home page
        btnHome.setOnClickListener{
            intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }
}