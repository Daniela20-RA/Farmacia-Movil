package com.example.menu.ui.Productos.producto

data class Producto(
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val fechaVencimiento: String,
    val categoria: String,
    val unidadMedida: String,
    val presentacion: String,
    val imagenUrl: String
)
