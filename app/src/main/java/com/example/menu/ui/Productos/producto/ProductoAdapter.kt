package com.example.menu.ui.Productos.producto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.menu.R

class ProductoAdapter(private var productos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvPrecio: TextView = itemView.findViewById(R.id.tvPrecio)
        val layoutDetalles: LinearLayout = itemView.findViewById(R.id.layoutDetalles)
        val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFechaVencimiento)
        val tvCategoria: TextView = itemView.findViewById(R.id.tvCategoria)
        val tvUnidad: TextView = itemView.findViewById(R.id.tvUnidadMedida)
        val tvPresentacion: TextView = itemView.findViewById(R.id.tvPresentacion)
        val btnVerDetalles: Button = itemView.findViewById(R.id.btnVerDetalles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_productos, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        holder.tvNombre.text = producto.nombre
        holder.tvPrecio.text = "Precio: $${producto.precio}"

        // Cargar imagen con Glide
        Glide.with(holder.itemView.context)
            .load(producto.imagenUrl)
            .placeholder(R.drawable.icon_medicament)
            .into(holder.imgProducto)

        // Mostrar/Ocultar detalles
        holder.btnVerDetalles.setOnClickListener {
            if (holder.layoutDetalles.visibility == View.GONE) {
                holder.layoutDetalles.visibility = View.VISIBLE
                holder.tvCantidad.text = "Cantidad: ${producto.cantidad}"
                holder.tvFecha.text = "Vence: ${producto.fechaVencimiento}"
                holder.tvCategoria.text = "Categoría: ${producto.categoria}"
                holder.tvUnidad.text = "Unidad: ${producto.unidadMedida}"
                holder.tvPresentacion.text = "Presentación: ${producto.presentacion}"
                holder.btnVerDetalles.text = "Ocultar"
            } else {
                holder.layoutDetalles.visibility = View.GONE
                holder.btnVerDetalles.text = "Ver detalles"
            }
        }

    }

    override fun getItemCount(): Int = productos.size


    fun actualizarLista(nuevaLista: List<Producto>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }


}