package com.example.locker.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.MainActivity
import com.example.locker.R
import com.example.locker.api.ApiClient
import com.example.locker.models.LoginRequest
import com.example.locker.models.LoginResponse
import com.example.locker.utils.TokenManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailField = findViewById<EditText>(R.id.emailField)
        val passwordField = findViewById<EditText>(R.id.passwordField)
        val loginButton = findViewById<Button>(R.id.loginButton)

        val tokenManager = TokenManager(this)

        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            val loginRequest = LoginRequest(email, password)

            ApiClient.instance.login(loginRequest)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val fullName = "${response.body()?.fullName}"
//                        Log.d("myTag", "${response.body()}")
//                        Log.d("myTag", "${call}")
                        if (response.body() != null) {
                            val fullName = response.body()?.fullName
                            if (fullName == "admin") {
                                val token = "Bearer ${response.body()?.token}"
                                tokenManager.saveToken(token)
                                Snackbar.make(findViewById(android.R.id.content), "Login Successful", Snackbar.LENGTH_SHORT).show()

                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()
                            } else {
                                Snackbar.make(findViewById(android.R.id.content), "Login Failed: Not an admin", Snackbar.LENGTH_SHORT).show()
                            }
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "Login Failed: ${response.message()}", Snackbar.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Snackbar.make(findViewById(android.R.id.content), "Error: ${t.message}", Snackbar.LENGTH_SHORT).show()
                    }
                })
        }
    }
}