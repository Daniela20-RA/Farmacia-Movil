package com.example.menu.ui.Factura

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.menu.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog
import android.widget.TextView

class fragmentFactura : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var facturaAdapter: FacturaAdapter
    private lateinit var facturas: List<Factura>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_factura, container, false)

        recyclerView = view.findViewById(R.id.recyclerFacturas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        facturas = cargarFacturas()
        facturaAdapter = FacturaAdapter(facturas) { factura ->
            mostrarDetalleFactura(factura)
        }

        recyclerView.adapter = facturaAdapter
        return view
    }

    private fun cargarFacturas(): List<Factura> {
        val json = requireContext().assets.open("factura.json")
            .bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<Factura>>() {}.type
        return Gson().fromJson(json, listType)
    }

    private fun mostrarDetalleFactura(factura: Factura) {
        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.dialog_factura_detalle, null)

        //val tvTitulo = view.findViewById<TextView>(R.id.tvTitulo)
        val tvCliente = view.findViewById<TextView>(R.id.tvCliente)
        val tvFecha = view.findViewById<TextView>(R.id.tvFecha)
        val tvDetalles = view.findViewById<TextView>(R.id.tvDetalles)
        val tvTotales = view.findViewById<TextView>(R.id.tvTotales)

        //tvTitulo.text = "Farmacia Salud Total"
        tvCliente.text = "Cliente: ${factura.clienteNombre}"
        tvFecha.text = "Fecha: ${factura.fecha}"

        val detallesTexto = StringBuilder()
        factura.detalles.forEach {
            detallesTexto.append("• ${it.producto}\n")
            detallesTexto.append("  Cantidad: ${it.cantidad}\n")
            detallesTexto.append("  Precio: C$${it.precio}\n")
            detallesTexto.append("  IVA: ${it.iva}%\n")
            detallesTexto.append("  Descuento: C$${it.descuento}\n\n")
        }

        tvDetalles.text = detallesTexto.toString()
        tvTotales.text = """
            Subtotal: C$${String.format("%.2f", factura.subtotal())}
            IVA: C$${String.format("%.2f", factura.totalIva())}
            Total: C$${String.format("%.2f", factura.total())}
        """.trimIndent()

        builder.setView(view)
        builder.setPositiveButton("Imprimir PDF") { _, _ ->
            try {
                FacturaPdf.generarFacturaPDF(requireContext(), factura)
                android.widget.Toast.makeText(requireContext(), "Factura generada correctamente ✅", android.widget.Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                android.widget.Toast.makeText(requireContext(), "Error al generar el PDF ❌", android.widget.Toast.LENGTH_LONG).show()
            }
        }
        builder.setNegativeButton("Cerrar", null)
        builder.show()
    }

}