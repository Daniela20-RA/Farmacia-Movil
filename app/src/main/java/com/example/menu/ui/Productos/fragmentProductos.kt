

package com.example.menu.ui.Productos

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.menu.R
import com.example.menu.ui.Productos.producto.Producto
import com.example.menu.ui.Productos.producto.ProductoAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class fragmentProductos : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private var productos: List<Producto> = emptyList()
    private lateinit var etBuscar: EditText
    private lateinit var spinnerFiltro: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_fragment_mis_productos, container, false)

        recyclerView = view.findViewById(R.id.recyclerProductos)
        etBuscar = view.findViewById(R.id.etBuscar)
        spinnerFiltro = view.findViewById(R.id.spinnerFiltro)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        try {
            productos = cargarProducto()
            adapter = ProductoAdapter(productos)
            recyclerView.adapter = adapter
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error cargando productos: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (::adapter.isInitialized && productos.isNotEmpty()) {
                    filtrarProductos(s.toString())
                }
            }
        })

        return view
    }

    private fun cargarProducto(): List<Producto> {
        val json = requireContext().assets.open("productos.json")
            .bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<Producto>>() {}.type
        return Gson().fromJson(json, listType)
    }

    private fun filtrarProductos(texto: String) {
        val textoMin = texto.lowercase().trim()
        val opcion = spinnerFiltro.selectedItem?.toString() ?: "Nombre"

        val filtrados = productos.filter {
            when (opcion) {
                "Nombre" -> it.nombre.lowercase().contains(textoMin)
                "Categoría" -> it.categoria.lowercase().contains(textoMin)
                "Unidad" -> it.unidadMedida.lowercase().contains(textoMin)
                "Presentación" -> it.presentacion.lowercase().contains(textoMin)
                else -> true
            }
        }

        adapter.actualizarLista(filtrados)
    }
}
