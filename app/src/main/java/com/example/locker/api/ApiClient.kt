package com.example.locker.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://aa7f-1-55-166-213.ngrok-free.app"

    val instance: ApiService by lazy {
        if (USE_MOCK) {
            MockApiService()
        } else {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    private const val USE_MOCK = true
}