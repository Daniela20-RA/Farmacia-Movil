package com.example.menu.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu.Models.Productoseneromarzo
import com.example.menu.Models.VentasProductos
import com.example.menu.Models.VentasResponse
import com.example.menu.Models.VentasSucursalAnio
import com.example.menu.Models.VentasSucursalResponse
import com.example.menu.Models.VentasPorDiaNombreSeptiembre
import com.example.menu.Models.ProductosClientes
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



    var productoseneromarzo by mutableStateOf<List<Productoseneromarzo>>(emptyList())
    fun cargarproductoseneromarzo(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getproductoseneromarzo()
                if (response.isSuccessful) {
                    productoseneromarzo = response.body() ?: emptyList()
                    Log.d("Response", "Producto de mes cargado correctamente: $productoseneromarzo")
                } else {
                    Log.d("Response", "Error ProductoMes ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error ProductoMes ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }



    var ventasseptiembre by mutableStateOf<List<VentasPorDiaNombreSeptiembre>>(emptyList())

    fun cargarventaseptiembre(context: Context) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getventasseptiembre()
                if (response.isSuccessful) {
                    ventasseptiembre = response.body() ?: emptyList()
                    Log.d("Response", "venta de mes cargado correctamente: $ventasseptiembre")
                } else {
                    Log.d("Response", "Error VentaMes ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error VentaMes ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    var productosClientes by mutableStateOf<List<ProductosClientes>>(emptyList())

    fun cargarProductosClientes(context: Context, nombreSucursal: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitHelper.api.getVentasProductosClientes(nombreSucursal)
                if (response.isSuccessful) {
                    productosClientes = response.body()?.filterNotNull() ?: emptyList()
                } else {
                    Log.d("Response", "Error productosClientes ${response.raw()}")
                }
            } catch (e: Exception) {
                Log.d("Response", "Error productosClientes ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}






