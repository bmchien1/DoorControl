package com.example.locker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.locker.api.ApiClient
import com.example.locker.models.HistoryItem

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val api = ApiClient.instance
        api.getHistory().enqueue(object : Callback<List<HistoryItem>> {
            override fun onResponse(call: Call<List<HistoryItem>>, response: Response<List<HistoryItem>>) {
                if (response.isSuccessful) {
                    val adapter = HistoryAdapter(response.body() ?: emptyList())
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<HistoryItem>>, t: Throwable) {
                // Handle error
            }
        })
    }
}
