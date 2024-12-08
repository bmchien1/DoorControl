package com.example.locker.utils

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("DoorControlPrefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }
}