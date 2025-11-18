package com.example.menu.Models


import com.google.gson.annotations.SerializedName


data class VentasSucursalAnio (

    @SerializedName("Sucursal" ) var Sucursal : String? = null,
    @SerializedName("Producto" ) var Producto : String? = null,
    @SerializedName("Anio"     ) var Anio     : Int?    = null,
    @SerializedName("Cantidad" ) var Cantidad : Int?    = null,
    @SerializedName("Precio"   ) var Precio   : Int?    = null,
    @SerializedName("Total"    ) var Total    : Int?    = null

)