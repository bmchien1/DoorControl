package com.example.locker.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.locker.R
import com.example.locker.api.ApiClient
import com.example.locker.models.SimpleResponse
import com.example.locker.utils.TokenManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UnlockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unlock)

        val unlockButton = findViewById<Button>(R.id.unlockButton)
        val tokenManager = TokenManager(this)
        val token = tokenManager.getToken()

        unlockButton.setOnClickListener {
            if (token != null) {
                ApiClient.instance.openDoor(token).enqueue(object : Callback<SimpleResponse> {
                    override fun onResponse(
                        call: Call<SimpleResponse>,
                        response: Response<SimpleResponse>
                    ) {
                        val message = "${response.body()?.message}"
                        if (message=="Success") {
                            Snackbar.make(findViewById(android.R.id.content), "Door unlocked successfully!", Snackbar.LENGTH_SHORT).show()
                        } else {
                            Snackbar.make(findViewById(android.R.id.content), "Failed to unlock door. Try again.", Snackbar.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                        Snackbar.make(findViewById(android.R.id.content), "Error: ${t.message}", Snackbar.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}

