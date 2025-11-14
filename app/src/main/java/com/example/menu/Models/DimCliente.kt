package com.example.menu.Models

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow

data class VentasResponse (

    @SerializedName("VentaKey"    ) var VentaKey    : Int?    = null,
    @SerializedName("Fecha"       ) var Fecha       : String? = null,
    @SerializedName("Cliente"     ) var Cliente     : String? = null,
    @SerializedName("Producto"    ) var Producto    : String? = null,
    @SerializedName("Sucursal"    ) var Sucursal    : String? = null,
    @SerializedName("TipoFactura" ) var TipoFactura : String? = null,
    @SerializedName("Cantidad"    ) var Cantidad    : Int?    = null,
    @SerializedName("Precio"      ) var Precio      : Int?    = null,
    @SerializedName("Iva"         ) var Iva         : Int?    = null,
    @SerializedName("Descuento"   ) var Descuento   : Int?    = null

)