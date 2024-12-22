package com.example.locker.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.locker.MainActivity
import com.example.locker.R
import com.example.locker.api.ApiClient
import com.example.locker.models.RegisterUserRequest
import com.example.locker.models.SimpleResponse
import com.example.locker.utils.TokenManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        val fullNameEditText = findViewById<EditText>(R.id.fullNameEditText)
        val birthdayEditText = findViewById<EditText>(R.id.birthdayEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val addButton = findViewById<Button>(R.id.addButton)

        val tokenManager = TokenManager(this)
        val token = tokenManager.getToken()

        addButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val birthday = birthdayEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()

            if (token != null && fullName.isNotEmpty() && birthday.isNotEmpty() && phone.isNotEmpty()) {
                // Register user
                val registerUserRequest = RegisterUserRequest(fullName, birthday, phone)
                ApiClient.instance.registerUser("Bearer $token", registerUserRequest)
                    .enqueue(object : Callback<SimpleResponse> {
                        override fun onResponse(
                            call: Call<SimpleResponse>,
                            response: Response<SimpleResponse>
                        ) {
                            val message = "${response.body()?.message}"
                            if (message == "Success") {
                                Snackbar.make(findViewById(android.R.id.content), "Card added successfully! Swipe the card now.", Snackbar.LENGTH_SHORT).show()
                            } else {
                                Snackbar.make(findViewById(android.R.id.content), "Failed to add card. Try again.", Snackbar.LENGTH_SHORT).show()
                            }
                            startActivity(Intent(this@AddCardActivity, MainActivity::class.java))
                        }

                        override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                            Snackbar.make(findViewById(android.R.id.content), "Error: ${t.message}", Snackbar.LENGTH_SHORT).show()
                        }
                    })
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
