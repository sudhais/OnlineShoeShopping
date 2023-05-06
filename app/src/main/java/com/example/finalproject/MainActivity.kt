package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.finalproject.spashScreen.splash

class MainActivity : AppCompatActivity() {
    lateinit var btnRegister: Button
    lateinit var btnUser : Button
    lateinit var btnAdmin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnRegister = findViewById(R.id.btnRegister)
        btnUser = findViewById(R.id.button3)
        btnAdmin = findViewById(R.id.button4)
        btnRegister.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
            //finish()
        }

        btnUser.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            //finish()
        }

        btnAdmin.setOnClickListener{

            val intent = Intent(this, adminPage::class.java)
            startActivity(intent)
            //finish()
        }
    }
}