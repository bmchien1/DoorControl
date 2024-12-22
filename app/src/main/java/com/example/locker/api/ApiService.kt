package com.example.locker.api

import com.example.locker.models.HistoryResponse
import com.example.locker.models.LoginRequest
import com.example.locker.models.LoginResponse
import com.example.locker.models.RegisterUserRequest
import com.example.locker.models.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("iot/api/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("iot/api/register-user")
    fun registerUser(
        @Header("Authorization") token: String,
        @Body registerUserRequest: RegisterUserRequest
    ): Call<SimpleResponse>

    @GET("iot/api/open-door")
    fun openDoor(@Header("Authorization") token: String): Call<SimpleResponse>

    @GET("iot/api/history")
    fun getHistory(@Header("Authorization") token: String): Call<HistoryResponse>


}
