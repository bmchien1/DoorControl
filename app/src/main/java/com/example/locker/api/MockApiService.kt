package com.example.locker.api

import com.example.locker.models.*
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockApiService : ApiService {

    override fun login(loginRequest: LoginRequest): Call<LoginResponse> {
        return object : Call<LoginResponse> {
            override fun enqueue(callback: Callback<LoginResponse>) {
                val mockResponse = LoginResponse("mockToken", "Admin", "admin")
                callback.onResponse(this, Response.success(mockResponse))
            }

            override fun execute(): Response<LoginResponse> =
                Response.success(LoginResponse("mockToken", "Admin", "admin"))

            override fun isExecuted(): Boolean = false
            override fun clone(): Call<LoginResponse> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun request(): okhttp3.Request = okhttp3.Request.Builder().build()
            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }
    }

    override fun registerUser(token: String, registerUserRequest: RegisterUserRequest): Call<SimpleResponse> {
        return object : Call<SimpleResponse> {
            override fun enqueue(callback: Callback<SimpleResponse>) {
                val mockResponse = SimpleResponse("Success")
                callback.onResponse(this, Response.success(mockResponse))
            }

            override fun execute(): Response<SimpleResponse> =
                Response.success(SimpleResponse("Success"))

            override fun isExecuted(): Boolean = false
            override fun clone(): Call<SimpleResponse> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun request(): okhttp3.Request = okhttp3.Request.Builder().build()
            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }
    }

    override fun checkCard(idCard: String): Call<SimpleResponse> {
        return object : Call<SimpleResponse> {
            override fun enqueue(callback: Callback<SimpleResponse>) {
                val mockResponse = SimpleResponse("2") // Simulate successful card registration
                callback.onResponse(this, Response.success(mockResponse))
            }

            override fun execute(): Response<SimpleResponse> =
                Response.success(SimpleResponse("2"))

            override fun isExecuted(): Boolean = false
            override fun clone(): Call<SimpleResponse> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun request(): okhttp3.Request = okhttp3.Request.Builder().build()
            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }
    }

    override fun openDoor(token: String): Call<SimpleResponse> {
        return object : Call<SimpleResponse> {
            override fun enqueue(callback: Callback<SimpleResponse>) {
                val mockResponse = SimpleResponse("Success") // Simulate successful door opening
                callback.onResponse(this, Response.success(mockResponse))
            }

            override fun execute(): Response<SimpleResponse> =
                Response.success(SimpleResponse("Success"))

            override fun isExecuted(): Boolean = false
            override fun clone(): Call<SimpleResponse> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun request(): okhttp3.Request = okhttp3.Request.Builder().build()
            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }
    }

    override fun getHistory(token: String): Call<HistoryResponse> {
        return object : Call<HistoryResponse> {
            override fun enqueue(callback: Callback<HistoryResponse>) {
                val mockData = listOf(
                    HistoryResponse.HistoryItem("Nguyen Dinh Vu", "xxxx-xxx-xxx", "13:30:20 07/12/2024"),
                    HistoryResponse.HistoryItem("Nguyen Dinh A", "xxxx-xxx-xxx", "13:30:20 07/12/2024")
                )
                val mockResponse = HistoryResponse(mockData)
                callback.onResponse(this, Response.success(mockResponse))
            }

            override fun execute(): Response<HistoryResponse> {
                val mockData = listOf(
                    HistoryResponse.HistoryItem("Nguyen Dinh Vu", "xxxx-xxx-xxx", "13:30:20 07/12/2024"),
                    HistoryResponse.HistoryItem("Nguyen Dinh A", "xxxx-xxx-xxx", "13:30:20 07/12/2024")
                )
                return Response.success(HistoryResponse(mockData))
            }

            override fun isExecuted(): Boolean = false
            override fun clone(): Call<HistoryResponse> = this
            override fun isCanceled(): Boolean = false
            override fun cancel() {}
            override fun request(): okhttp3.Request = okhttp3.Request.Builder().build()
            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }
    }
}
