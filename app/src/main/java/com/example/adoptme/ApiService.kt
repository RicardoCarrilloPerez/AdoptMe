package com.example.adoptme

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {

    @GET("query/get_estados?token=13c80280-9414-407a-918c-0fa0d0968ca5")
    fun getEstados(): Call<ApiResponse>

}

data class ApiResponse(
    val error: Boolean,
    val code_error: Int,
    val error_message: String?,
    val response: Response
)

data class Response(
    val estado: List<String>
)

