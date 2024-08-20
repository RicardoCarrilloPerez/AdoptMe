package com.example.adoptme

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HuachitosApiService {
    @GET("animales/tipo/{tipo}")
    fun getAnimalesPorTipo(@Path("tipo") tipo: String): Call<Animal>
}
