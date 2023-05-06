package com.example.finalproject.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.database.entities.User
import com.example.finalproject.database.repositories.userRepo
import com.example.finalproject.database.userDatabase
import com.example.finalproject.updatePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class userAdapter(): RecyclerView.Adapter<userAdapter.ViewHolder>() {

    lateinit var data:List<User>
    lateinit var context: Context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val name:TextView
        val email:TextView
        val phone:TextView
        val username:TextView
        val password:TextView
        val btnDelete:ImageView
        //val btnUpdate:ImageView
        val id:TextView
        init {
            id = view.findViewById(R.id.txtId)
            name= view.findViewById(R.id.txtName)
            email = view.findViewById(R.id.txtEmail)
            phone = view.findViewById(R.id.txtPhone)
            username = view.findViewById(R.id.txtUsername)
            password = view.findViewById(R.id.txtPassword)
            btnDelete = view.findViewById(R.id.btnUDelete)
           // btnUpdate = view.findViewById(R.id.btnUpdate)
        }


    }

    fun setData(data:List<User>,context: Context){

        this.data = data
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val currentUser = data[position]
        holder.id.text = currentUser.id.toString()
        holder.name.text = data[position].name
        holder.email.text = currentUser.email
        holder.phone.text = currentUser.phoneNo
        holder.username.text = currentUser.username
        holder.password.text = currentUser.password

        holder.btnDelete.setOnClickListener{
            val repository = userRepo(userDatabase.getInstance(context))
            holder.btnDelete.setImageResource(R.drawable.clicked_delete_image)
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteUser(data[position])
                val data = repository.readAllUsers()
                withContext(Dispatchers.Main){
                    setData(data,context)
                    holder.btnDelete.setImageResource(R.drawable.delete_icon)
                }
            }
            Toast.makeText(context,"Successfully Deleted the user", Toast.LENGTH_LONG).show()
        }

//        holder.btnUpdate.setOnClickListener{
//        }



    }
}