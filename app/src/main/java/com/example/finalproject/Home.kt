package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.itemAdapter
import com.example.finalproject.models.Shoe_item
import java.util.*
import kotlin.collections.ArrayList

class Home : AppCompatActivity() {

    lateinit var btnCart:ImageView
    lateinit var logout:Button

    lateinit var newRecyclerView: RecyclerView
    lateinit var newArrayList: ArrayList<Shoe_item>
    lateinit var tempArrayList: ArrayList<Shoe_item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newRecyclerView = findViewById(R.id.itemRecycle)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        //set the shoe details in arrayList
        newArrayList = arrayListOf<Shoe_item>()
        tempArrayList = arrayListOf<Shoe_item>()
        newArrayList.add(Shoe_item(R.drawable.adidas,"addidas","2600"))
        newArrayList.add(Shoe_item(R.drawable.shoe10, "Sketchers", "2333"))
        newArrayList.add(Shoe_item(R.drawable.shoe7, "Adidas Air retro", "3877"))
        newArrayList.add(Shoe_item(R.drawable.shoe1, "Nike jordan", "5999"))

        tempArrayList.addAll(newArrayList)

        newRecyclerView.adapter = itemAdapter(tempArrayList, this)

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())

                if(searchText.isNotEmpty()){
                    newArrayList.forEach{

                        if(it.name.lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }

                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(newArrayList)
                    newRecyclerView.adapter!!.notifyDataSetChanged()
                }

                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}