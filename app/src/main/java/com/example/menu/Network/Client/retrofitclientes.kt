package com.example.menu.Network.Client

import com.example.menu.Models.Cliente
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers



interface retrofitclientes {

    @Headers("Content-Type: application/json")
    @GET("/Clientes")
    suspend fun getclientes(
    ): Response<List<Cliente>>
}