package com.example.menu.Models

import kotlinx.coroutines.flow.MutableStateFlow

data class DimCliente(
    val clienteKey: Int,
    val nombres: String,
    val apellido1: String,
    val apellido2: String,
    val telefono: String,
    val direccion: String
)