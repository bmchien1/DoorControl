package com.example.locker.models

data class RegisterUserRequest(
    val fullName: String,
    val birthday: String,
    val phone: String
)
