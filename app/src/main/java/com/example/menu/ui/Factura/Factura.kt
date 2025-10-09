package com.example.menu.ui.Factura

data class DetalleFactura(
    val producto: String,
    val cantidad: Int,
    val precio: Double,
    val iva: Double,
    val descuento: Double
)

data class Factura(
    val codigo: String,
    val fecha: String,
    val clienteId: String,
    val clienteNombre: String?,
    val tipoFacturaId: String,
    val detalles: List<DetalleFactura>
) {
    fun subtotal(): Double {
        return detalles.sumOf { (it.precio * it.cantidad) - it.descuento }
    }

    fun totalIva(): Double {
        return detalles.sumOf {
            val base = (it.precio * it.cantidad) - it.descuento
            base * (it.iva / 100.0)
        }
    }

    fun total(): Double = subtotal() + totalIva()
}