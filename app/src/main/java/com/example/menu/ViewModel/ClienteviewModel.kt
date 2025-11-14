package com.example.menu.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu.Models.VentasResponse
import com.example.menu.Network.RetrofitHelper
import kotlinx.coroutines.launch

class ClienteviewModel : ViewModel() {

    var clientes by mutableStateOf<List<VentasResponse>>(emptyList())

    fun cargarClientes(context: Context) {
        viewModelScope.launch {
            try {
                Log.d("Response", "Intentando cargar clientes")
                val Response = RetrofitHelper.api.getClientes()
                if(Response.isSuccessful) {
                    clientes = Response.body() ?: emptyList()
                    Log.d("Response", "Clientes cargados correctamente")
                } else {
                    Log.d("Response", "Error al cargar clientes ${Response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error al cargar clientes ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}

