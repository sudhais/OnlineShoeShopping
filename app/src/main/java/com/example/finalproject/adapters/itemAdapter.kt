package com.example.finalproject.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.database.entities.Cart
import com.example.finalproject.database.repositories.userRepo
import com.example.finalproject.database.userDatabase
import com.example.finalproject.models.Shoe_item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class itemAdapter(val itemlist: ArrayList<Shoe_item>, context: Context): RecyclerView.Adapter<itemAdapter.MyViewHolder>() {


    var context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)

        return MyViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

//    fun setData(context: Context) {
//        this.context = context
//        notifyDataSetChanged()
//    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemlist[position]
        holder.image.setImageResource(currentItem.image)
        holder.name.text = currentItem.name
        holder.price.text = currentItem.price

//        val imgid = holder.btnCart.drawable
//        Log.e("111", "${imgid}")
        holder.btnCart.setOnClickListener{
            val repository = userRepo(userDatabase.getInstance(context))
            val cart = Cart(0,currentItem.name,currentItem.image,currentItem.price.toDouble(),1,currentItem.price.toDouble())


            CoroutineScope(Dispatchers.IO).launch {
                repository.addCart(cart)
            }
            holder.btnCart.setImageResource(R.drawable.selected_cart)
            Toast.makeText(context,"Successfully added to the Cart", Toast.LENGTH_LONG).show()

//            if(holder.btnCart.id == R.drawable.selected_cart){
//
////                CoroutineScope(Dispatchers.IO).launch {
////                    repository.deleteCart(cart)
////                }
//                holder.btnCart.setImageResource(R.drawable.unselected_cart)
//
//            }


        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val image: ImageView = itemView.findViewById(R.id.imageTitle)
        val name: TextView = itemView.findViewById(R.id.name_shoe)
        val price: TextView = itemView.findViewById(R.id.price_shoe)
        val btnCart:ImageView = itemView.findViewById(R.id.btnCart)
    }
}