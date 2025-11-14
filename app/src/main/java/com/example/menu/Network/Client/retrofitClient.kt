package com.example.menu.Network.Client

import com.example.menu.Models.VentasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface retrofitClient {
    @Headers("Content-Type: application/json")
    @GET("FactVentas/DTO")
    suspend fun getClientes():
            Response<List<VentasResponse>>
}