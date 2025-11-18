package com.example.menu.Network.Client

import com.example.menu.Models.VentasProductos
import com.example.menu.Models.VentasResponse
import com.example.menu.Models.VentasSucursalAnio
import com.example.menu.Models.VentasSucursalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface retrofitClient {
    @Headers("Content-Type: application/json")
    @GET("FactVentas/DTO")
    suspend fun getClientes():
            Response<List<VentasResponse>>

    // VentasSucursalResponse ENDPOINT

    @Headers("Content-Type: application/json")
    @GET("api/ventas/sucursal/{nombreSucursal}")
    suspend fun getVentasSucursal(
        @Path("nombreSucursal") nombreSucursal: String
    ): Response<List<VentasSucursalResponse>>


    @Headers("Content-Type: application/json")
    @GET("/api/ventas/productos")
    suspend fun getVentasProductos(
    ): Response<List<VentasProductos>>

    @Headers("Content-Type: application/json")
    @GET("/api/ventas/sucursal-producto-anio")
    suspend fun getVentasSucursalAnio(
    ): Response<List<VentasSucursalAnio>>


}