package com.example.locker.api

import com.example.locker.models.HistoryItem
import com.example.locker.models.UnlockRequest
import com.example.locker.models.UnlockResponse
import com.example.locker.models.User
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("unlock")
    fun unlock(@Body request: UnlockRequest): Call<UnlockResponse>

    @GET("history")
    fun getHistory(): Call<List<HistoryItem>>

    @POST("user")
    fun updateUser(@Body user: User): Call<User>
}
