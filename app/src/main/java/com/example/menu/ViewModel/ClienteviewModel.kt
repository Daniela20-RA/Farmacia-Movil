package com.example.menu.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu.Models.VentasProductos
import com.example.menu.Models.VentasResponse
import com.example.menu.Models.VentasSucursalAnio
import com.example.menu.Models.VentasSucursalResponse
import com.example.menu.Network.RetrofitHelper
import kotlinx.coroutines.launch

class ClienteviewModel : ViewModel() {

    // CLIENTES
    var clientes by mutableStateOf<List<VentasResponse>>(emptyList())

    fun cargarClientes(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getClientes()
                if (response.isSuccessful) {
                    clientes = response.body()?.filterNotNull() ?: emptyList()
                } else {
                    Log.d("Response", "Error al cargar clientes ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error al cargar clientes ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // VENTAS POR SUCURSAL
    var ventasSucursal by mutableStateOf<List<VentasSucursalResponse>>(emptyList())

    fun cargarVentasSucursal(context: Context, nombreSucursal: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getVentasSucursal(nombreSucursal)
                if (response.isSuccessful) {
                    ventasSucursal = response.body()?.filterNotNull() ?: emptyList()
                } else {
                    Log.d("Response", "Error sucursal ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error sucursal ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // VENTAS POR PRODUCTOS
    var ventasProductos by mutableStateOf<List<VentasProductos>>(emptyList())

    fun cargarProductos(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getVentasProductos()
                if (response.isSuccessful) {
                    ventasProductos = response.body()?.filterNotNull() ?: emptyList()
                } else {
                    Log.d("Response", "Error productos ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error productos ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // VENTAS POR SUCURSAL – AÑO
    var ventasSucursalAnio by mutableStateOf<List<VentasSucursalAnio>>(emptyList())

    fun cargarSucursalAnio(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getVentasSucursalAnio()
                if (response.isSuccessful) {
                    ventasSucursalAnio = response.body() ?: emptyList()
                    Log.d("Response", "Sucursal año cargado correctamente: $ventasSucursalAnio")
                } else {
                    Log.d("Response", "Error sucursalAnio ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error sucursalAnio ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}




