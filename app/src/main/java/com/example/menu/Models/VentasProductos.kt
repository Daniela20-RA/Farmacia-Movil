package com.example.menu.Models

import com.google.gson.annotations.SerializedName


data class VentasProductos (

    @SerializedName("Producto" ) var Producto : String? = null,
    @SerializedName("Cantidad" ) var Cantidad : Int?    = null,
    @SerializedName("Precio"   ) var Precio   : Int?    = null,
    @SerializedName("Total"    ) var Total    : Int?    = null

)