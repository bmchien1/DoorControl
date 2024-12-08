package com.example.locker.models

data class LoginResponse(
    val token: String,
    val fullName: String,
    val role: String
)
