package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.itemAdapter
import com.example.finalproject.models.Shoe_item

class Home : AppCompatActivity() {

    lateinit var btnCart:ImageView
    lateinit var logout:Button

    lateinit var newRecyclerView: RecyclerView
    lateinit var newArrayList: ArrayList<Shoe_item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newRecyclerView = findViewById(R.id.itemRecycle)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf<Shoe_item>()
        newArrayList.add(Shoe_item(R.drawable.adidas,"addidas","2600"))
        newArrayList.add(Shoe_item(R.drawable.shoe10, "Sketchers", "2333"))
        newArrayList.add(Shoe_item(R.drawable.shoe7, "Adidas Air retro", "3877"))
        newArrayList.add(Shoe_item(R.drawable.shoe1, "Nike jordan", "5999"))

        newRecyclerView.adapter = itemAdapter(newArrayList, this)

        btnCart = findViewById(R.id.imagecart1)
        logout = findViewById(R.id.logoutb)

        btnCart.setOnClickListener{

            val intent = Intent(this, CartPage::class.java)
            startActivity(intent)
            //finish()
        }

        logout.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //finish()
        }

    }
}