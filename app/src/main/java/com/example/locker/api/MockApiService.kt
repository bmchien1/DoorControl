// MockApiService.kt
package com.example.locker.api

import com.example.locker.models.HistoryResponse
import com.example.locker.models.LoginRequest
import com.example.locker.models.LoginResponse
import com.example.locker.models.RegisterUserRequest
import com.example.locker.models.SimpleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

//class MockApiService : ApiService {
//
//    override fun login(loginRequest: LoginRequest): Call<LoginResponse> {
//        val mockResponse = LoginResponse("mockToken", "Admin", "admin")
//        return Call.success(mockResponse)
//    }
//
//    override fun registerUser(
//        @Header(value = "Authorization") token: String,
//        @Body registerRequest: RegisterUserRequest
//    ): Call<SimpleResponse> {
//        val mockResponse = SimpleResponse("Success")
//        return Call.success(mockResponse)
//    }
//
//    override fun checkCard(idCard: String): Call<SimpleResponse> {
//        val mockResponse = SimpleResponse("2")  // Simulate successful card registration
//        return Call.success(mockResponse)
//    }
//
//    override fun openDoor(@Header(value = "Authorization") token: String): Call<SimpleResponse> {
//        val mockResponse = SimpleResponse("Success")  // Simulate successful door opening
//        return Call.success(mockResponse)
//    }
//}
