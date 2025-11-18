package com.example.menu

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface ApiService {

    // Obtener clientes
    @GET("catalogos/clientes/")
    suspend fun getclientes(): List<client>

    // Enviar un cliente nuevo
    @POST("catalogos/clientes/")
    suspend fun createCliente(@Body nuevo: client): client
}

