package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.userAdapter
import com.example.finalproject.database.repositories.userRepo
import com.example.finalproject.database.userDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class adminPage : AppCompatActivity() {

    lateinit var btnUpdate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_page)

        btnUpdate = findViewById(R.id.updateBtn)
        val btnLogout = findViewById<Button>(R.id.btnBack)

        val repository = userRepo(userDatabase.getInstance(this))
        val recyclerView: RecyclerView = findViewById(R.id.userRecycle)
        //recyclerView.setHasFixedSize(true)
        val ui = this
        val adapter = userAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(ui)


        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.readAllUsers()
            //recyclerView.adapter=userAdapter(data)
            Log.e("000", "${data}")
            adapter.setData(data,ui)



           // runOnUiThread{
                //adapter.setData(data,this@adminPage)
            //}

            //repository.readAllUsers()

        }

        btnUpdate.setOnClickListener{
            displayDialog()
        }

        btnLogout.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    private fun displayDialog() {

        //create new instance of AlertDialog.Builder
        val builder = AlertDialog.Builder(this)

        //set alert dialog title and the message
        builder.setTitle("Enter User ID:")
        builder.setMessage("Enter the User ID below:")

        //create an EditText input field
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        //set the positive button action
        builder.setPositiveButton("Ok") {dialog, which ->
            //Get the input text and display a toast message
            val id = input.text.toString().toInt()
            val intent = Intent(this, updatePage::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
            finish()
        }

        //set the negative button action
        builder.setNegativeButton("Cancel") {dialog, which ->
            dialog.cancel()
        }

        //create and show the alert box
        val alertDialog = builder.create()
        alertDialog.show()
    }

}