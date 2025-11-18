package com.example.menu.Models

import com.google.gson.annotations.SerializedName

data class VentasSucursalResponse (

    @SerializedName("Cliente"  ) var Cliente  : String? = null,
    @SerializedName("Cantidad" ) var Cantidad : Int?    = null,
    @SerializedName("Total"    ) var Total    : Int?    = null

)