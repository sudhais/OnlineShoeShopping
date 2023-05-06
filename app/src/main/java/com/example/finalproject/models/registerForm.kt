package com.example.finalproject.models

import com.example.finalproject.models.validations.validateForm

class registerForm(
    private var name:String,
    private var email:String,
    private var phoneNo:String,
    private var username:String,
    private var password:String

) {

    //validate the name
    fun validateName():validateForm{
        return if(name.isEmpty()){
            validateForm.Empty("Name is empty")
        }else if (name.length <= 6) {
            validateForm.Invalid("Name should minimum 6 letters")
        }else{
            validateForm.Valid
        }
    }

    //validate the email
    fun validateEmail():validateForm{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return if(email.isEmpty()){
            validateForm.Empty("Email is empty")
        }else if(!email.matches(emailRegex.toRegex())){
            validateForm.Invalid("Invalid email address")
        }else{
            validateForm.Valid
        }
    }

    //validate the phone number
    fun validatePhoneNo():validateForm{
        return if(phoneNo.isEmpty()) {
            validateForm.Empty("phone no is empty")
        }else if(phoneNo.length > 10){
            validateForm.Invalid("phone no should be 10 digts")
        }else if(phoneNo.length < 10){
            validateForm.Invalid("phone no should be 10 digts")
        }else {
            validateForm.Valid
        }

    }

    //validate username
    fun validateUsername():validateForm{
        return if(username.isEmpty()){
            validateForm.Empty("Username is empty")
        }else if (username.length <= 8) {
            validateForm.Invalid("Name should minimum 8 letters")
        }else{
            validateForm.Valid
        }
    }

    //validate the password
    fun validatePassword():validateForm{
        return if(password.isEmpty()){
            validateForm.Empty("password is empty")
        }else if (password.length <= 8) {
            validateForm.Invalid("password should minimum 8 letters")
        }else{
            validateForm.Valid
        }
    }

}