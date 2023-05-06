package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.finalproject.database.entities.User
import com.example.finalproject.database.repositories.userRepo
import com.example.finalproject.database.userDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class updatePage : AppCompatActivity() {
    lateinit var btnUpdate:Button
    lateinit var user:User
    val ui = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_page)

        btnUpdate = findViewById(R.id.btnUp)
        var txtName = findViewById<TextView>(R.id.edtNameUp)
        var txtEmail = findViewById<TextView>(R.id.edtEmail)
        var txtPhone = findViewById<TextView>(R.id.edtPhone)
        var txtUsername = findViewById<TextView>(R.id.edtUName)
        var txtPassword = findViewById<TextView>(R.id.edtPassword)

        val id = intent.getIntExtra("id", 0)

        val repository = userRepo(userDatabase.getInstance(this))

        CoroutineScope(Dispatchers.IO).launch {
            user = repository.getUserById(id)
            Log.e("0001", "${user}")
            txtName.setText(user.name)
            txtEmail.setText(user.email)
            txtPhone.setText(user.phoneNo)
            txtUsername.setText(user.username)
            txtPassword.setText(user.password)
        }

        btnUpdate.setOnClickListener {


            val userUp = User(
                user.id,
                txtName.text.toString(),
                txtEmail.text.toString(),
                txtPhone.text.toString(),
                txtUsername.text.toString(),
                txtPassword.text.toString()
            )

            CoroutineScope(Dispatchers.IO).launch{
                repository.updateUser(userUp)
                Log.e("0011", "${userUp}")
                val inten = Intent(ui, adminPage::class.java)
                startActivity(inten)
                finish()
            }


        }
    }
}