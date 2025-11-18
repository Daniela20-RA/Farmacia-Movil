package com.example.menu.Models

import com.google.gson.annotations.SerializedName


data class VentasPorDiaNombreSeptiembre (

    @SerializedName("Dia"       ) var Dia       : Int?    = null,
    @SerializedName("NombreDia" ) var NombreDia : String? = null,
    @SerializedName("Total"     ) var Total     : Int?    = null

)