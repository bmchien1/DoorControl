package com.example.locker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.locker.HistoryAdapter
import com.example.locker.R
import com.example.locker.api.ApiClient
import com.example.locker.models.HistoryResponse
import com.example.locker.utils.TokenManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tokenManager = TokenManager(this)
        val token = tokenManager.getToken()

        if (token != null) {
            ApiClient.instance.getHistory(token).enqueue(object : Callback<HistoryResponse> {
                override fun onResponse(
                    call: Call<HistoryResponse>,
                    response: Response<HistoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val historyList = response.body()?.data
                        recyclerView.adapter = HistoryAdapter(historyList ?: emptyList())
                    } else {
                        Toast.makeText(this@HistoryActivity, "Failed to load history", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                    Toast.makeText(this@HistoryActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}