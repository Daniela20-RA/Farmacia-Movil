package com.example.menu.Models

import com.google.gson.annotations.SerializedName


data class Productoseneromarzo (

    @SerializedName("Sucursal" ) var Sucursal : String? = null,
    @SerializedName("Producto" ) var Producto : String? = null,
    @SerializedName("Cantidad" ) var Cantidad : Int?    = null

)