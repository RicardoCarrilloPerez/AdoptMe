package com.example.adoptme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object RetrofitClient {
    private const val BASE_URL = "https://api.copomex.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}
object RetrofitClientH {
    private const val BASE_URL = "https://huachitos.cl/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: HuachitosApi by lazy {
        retrofit.create(HuachitosApi::class.java)
    }
}

interface HuachitosApi {
    @GET("animales")
    suspend fun getAnimales(): AnimalResponse

    @GET("animales/tipo/{tipo}")
    suspend fun getAnimalesByTipo(@Path("tipo") tipo: String): AnimalResponse

}

