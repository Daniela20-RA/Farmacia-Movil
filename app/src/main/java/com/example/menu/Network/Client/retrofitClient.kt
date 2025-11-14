package com.example.menu.Network.Client

import com.example.menu.Models.DimCliente
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface retrofitClient {
    @Headers("Content-Type: text/json")
    @GET("DimClientes/DTO")
    suspend fun getClientes():
            Response<DimCliente>
}