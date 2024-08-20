package com.example.adoptme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstadosViewModel : ViewModel() {

    private val _estados = MutableLiveData<List<Estado>>()
    val estados: LiveData<List<Estado>> get() = _estados

    fun fetchEstados() {
        val call = RetrofitClient.apiService.getEstados()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        val estadosList = apiResponse.response.estado.map { Estado(it) }
                        _estados.postValue(estadosList)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Manejo de errores
            }
        })
    }
}
