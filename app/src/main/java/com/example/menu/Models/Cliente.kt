package com.example.menu.Models

import com.google.gson.annotations.SerializedName


data class Cliente (

    @SerializedName("id"              ) var id              : String? = null,
    @SerializedName("codigo"          ) var codigo          : String? = null,
    @SerializedName("nombres"         ) var nombres         : String? = null,
    @SerializedName("primerApellido"  ) var primerApellido  : String? = null,
    @SerializedName("telefono"        ) var telefono        : String? = null,
    @SerializedName("segundoApellido" ) var segundoApellido : String? = null,
    @SerializedName("direccion"       ) var direccion       : String? = null

)