package com.example.menu.ui.Factura

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R

class FacturaAdapter(
    private val facturas: List<Factura>,
    private val onVerFacturaClick: (Factura) -> Unit
) : RecyclerView.Adapter<FacturaAdapter.FacturaViewHolder>() {

    class FacturaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
        val tvCliente: TextView = view.findViewById(R.id.tvCliente)
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
        val btnVerFactura: Button = view.findViewById(R.id.btnVerFactura)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_factura, parent, false)
        return FacturaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val factura = facturas[position]
        holder.tvCodigo.text = "Código: ${factura.codigo}"
        holder.tvCliente.text = "Cliente: ${factura.clienteNombre}"
        holder.tvFecha.text = "Fecha: ${factura.fecha}"

        holder.btnVerFactura.setOnClickListener {
            onVerFacturaClick(factura)
        }
    }

    override fun getItemCount(): Int = facturas.size
}