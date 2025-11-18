package com.example.menu.ui.Productos.producto


import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object ProductoApiService {

    fun obtenerProductos(context: Context): List<Producto> {
        val jsonString: String
        try {
            jsonString = context.assets.open("productos.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val gson = Gson()
        val tipoLista = object : TypeToken<List<Producto>>() {}.type
        return gson.fromJson(jsonString, tipoLista)
    }
}
