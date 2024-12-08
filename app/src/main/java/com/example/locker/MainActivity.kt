package com.example.locker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.ui.AddCardActivity
import com.example.locker.ui.HistoryActivity
import com.example.locker.ui.LoginActivity
import com.example.locker.utils.TokenManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddCard = findViewById<Button>(R.id.btnAddCard)
        val btnViewHistory = findViewById<Button>(R.id.btnViewHistory)
        val btnUnlockDoor = findViewById<Button>(R.id.btnUnlockDoor)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val tokenManager = TokenManager(this)

        // Check if user is logged in
        val token = tokenManager.getToken()
        if (token == null) {
            // Redirect to login if token is null
            navigateToLogin()
            return
        }

        // Add card feature
        btnAddCard.setOnClickListener {
            startActivity(Intent(this, AddCardActivity::class.java))
        }

        // View history feature
        btnViewHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        // Unlock door feature
        btnUnlockDoor.setOnClickListener {
            unlockDoor(token)
        }

        // Logout
        btnLogout.setOnClickListener {
            tokenManager.clearToken()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            navigateToLogin()
        }
    }

    private fun unlockDoor(token: String) {
        // Call unlock door API
        val retrofit = com.example.locker.api.ApiClient.instance
        retrofit.openDoor("Bearer $token").enqueue(object : retrofit2.Callback<com.example.locker.models.SimpleResponse> {
            override fun onResponse(
                call: retrofit2.Call<com.example.locker.models.SimpleResponse>,
                response: retrofit2.Response<com.example.locker.models.SimpleResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Door unlocked successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to unlock the door", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<com.example.locker.models.SimpleResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}