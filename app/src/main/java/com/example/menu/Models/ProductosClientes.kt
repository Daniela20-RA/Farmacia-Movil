package com.example.menu.Models

import com.google.gson.annotations.SerializedName


data class ProductosClientes (

    @SerializedName("Producto" ) var Producto : String? = null,
    @SerializedName("Cliente"  ) var Cliente  : String? = null,
    @SerializedName("Total"    ) var Total    : Int?    = null

)