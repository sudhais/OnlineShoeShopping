package com.example.finalproject

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.finalproject.database.entities.User
import com.example.finalproject.database.repositories.userRepo
import com.example.finalproject.database.userDatabase
import com.example.finalproject.models.registerForm
import com.example.finalproject.models.validations.validateForm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class register : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var email: EditText
    lateinit var phoneNo:EditText
    lateinit var username:EditText
    lateinit var password:EditText

    private var valid = 0

    lateinit var repository:userRepo

    //private  lateinit var mUserViewModel : userViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        repository = userRepo(userDatabase.getInstance(this))

        name = findViewById(R.id.edtNameUp)
        email = findViewById(R.id.edtEmail)
        phoneNo = findViewById(R.id.edtPhone)
        username = findViewById(R.id.edtUName)
        password = findViewById(R.id.edtPassword)
        val back:Button = findViewById(R.id.buttonBack)

        //redirect to the login page when click the back button
        back.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //mUserViewModel = ViewModelProvider(this).get(userViewModel::class.java)
    }

//    fun displayAlert(title:String, message:String){
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle(title)
//        builder.setMessage(message)
//        builder.setPositiveButton("OK") { dialog, which ->
//            // Do something when the "OK" button is clicked
//        }
//        val dialog = builder.create()
//        dialog.show()
//    }

    fun submit(v:View){
        val Form =  registerForm(
            name.text.toString(),
            email.text.toString(),
            phoneNo.text.toString(),
            username.text.toString(),
            password.text.toString()
        )

        val nameValidation = Form.validateName()
        val emailValidation = Form.validateEmail()
        val phoneValidation = Form.validatePhoneNo()
        val usernameValidation = Form.validateUsername()
        val passwordValidation = Form.validatePassword()

        when(nameValidation){
            is validateForm.Valid -> {
                valid++
            }
            is validateForm.Invalid -> {
                name.error = nameValidation.errorMessage
            }
            is validateForm.Empty -> {
                name.error = nameValidation.errorMessage
            }
        }

        when(emailValidation){
            is validateForm.Valid -> {
                valid++
            }
            is validateForm.Invalid -> {
                email.error = emailValidation.errorMessage
            }
            is validateForm.Empty -> {
                email.error = emailValidation.errorMessage
            }
        }

        when(phoneValidation){
            is validateForm.Valid -> {
                valid++
            }
            is validateForm.Invalid -> {
                phoneNo.error = phoneValidation.errorMessage
            }
            is validateForm.Empty -> {
                phoneNo.error = phoneValidation.errorMessage
            }
        }

        when(usernameValidation){
            is validateForm.Valid -> {
                valid++
            }
            is validateForm.Invalid -> {
                username.error = usernameValidation.errorMessage
            }
            is validateForm.Empty -> {
                username.error = usernameValidation.errorMessage
            }
        }

        when(passwordValidation){
            is validateForm.Valid -> {
                valid++
            }
            is validateForm.Invalid -> {
                password.error = passwordValidation.errorMessage
            }
            is validateForm.Empty -> {
                password.error = passwordValidation.errorMessage
            }
        }

        if(valid == 5){
            //displayAlert("success", "successfully registered")

            val user = User(
                0,
                name.text.toString(),
                email.text.toString(),
                phoneNo.text.toString(),
                username.text.toString(),
                password.text.toString()
            )
            //add the user into user_table
            CoroutineScope(Dispatchers.IO).launch {
                repository.addUser(user)
                Log.e("112233", "${user}")
            }
            //mUserViewModel.addUser(user)
            //Toast.makeText(this,"Successfully added", Toast.LENGTH_LONG).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}