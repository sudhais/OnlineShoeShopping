package com.example.finalproject.models.validations

sealed class validateForm {

    data class Empty(val errorMessage:String):validateForm()
    data class Invalid(val errorMessage: String):validateForm()
    object Valid:validateForm()
}
