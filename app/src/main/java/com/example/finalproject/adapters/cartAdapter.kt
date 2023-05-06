package com.example.finalproject.adapters

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class cartAdapter: RecyclerView.Adapter<cartAdapter.MyViewHolder>(){

    lateinit var data :List<Cart>
    lateinit var context:Context

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val shoeImage: ImageView = itemView.findViewById(R.id.shoeImage)
        val shoeName: TextView = itemView.findViewById(R.id.name)
        val btnPlus: ImageView = itemView.findViewById(R.id.btnPlus)
        val quantity: TextView = itemView.findViewById(R.id.count)
        val btnMinus: ImageView = itemView.findViewById(R.id.btnMinus)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
        val shoePrice: TextView = itemView.findViewById(R.id.price)
        val totPrice: TextView = itemView.findViewById(R.id.priceTot)

    }

    fun setData(data:List<Cart>, context: Context){
        this.data = data
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)

        return MyViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = data[position]
        holder.shoeName.text = currentItem.shoeName
        holder.shoeImage.setImageResource(currentItem.shoeImage)
        holder.shoePrice.text = currentItem.shoePrice.toString()
        holder.quantity.text = currentItem.quanity.toString()
        holder.totPrice.text = currentItem.totPrice.toString()

        val repository = userRepo(userDatabase.getInstance(context))
        holder.btnDelete.setOnClickListener{


            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteCart(data[position])
                val data = repository.readAllCart()
                withContext(Dispatchers.Main){
                    setData(data, context)
                }
            }
            Toast.makeText(context,"Successfully Deleted from cart ", Toast.LENGTH_LONG).show()
        }

        holder.btnPlus.setOnClickListener{
            var quan = currentItem.quanity
            CoroutineScope(Dispatchers.IO).launch{
                quan++
                repository.updateCart(Cart(currentItem.id,currentItem.shoeName,currentItem.shoeImage,currentItem.shoePrice,quan,quan*currentItem.shoePrice))

                val data1 = repository.readAllCart()
                Log.e("12345", "${data1}")
                withContext(Dispatchers.Main){
                    setData(data1, context)
                }
            }
        }

        holder.btnMinus.setOnClickListener{
            var quan = currentItem.quanity
            if(currentItem.quanity > 0) {
                CoroutineScope(Dispatchers.IO).launch{
                    quan--
                    repository.updateCart(Cart(currentItem.id,currentItem.shoeName,currentItem.shoeImage,currentItem.shoePrice,quan,quan*currentItem.shoePrice))

                    val data2 = repository.readAllCart()
                    Log.e("123456", "${data2}")
                    withContext(Dispatchers.Main){
                        setData(data2, context)
                    }
                }

            }
        }

    }


}