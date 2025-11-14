package com.example.menu.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menu.Models.DimCliente
import com.example.menu.Network.RetrofitHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClienteviewModel : ViewModel() {

    var clientes by mutableStateOf<DimCliente?>(null)

    fun cargarClientes(context: Context) {
        viewModelScope.launch {
            try {
                Log.d("Response", "Intentando cargar clientes")
                val Response = RetrofitHelper.api.getClientes()
                clientes = Response.body()
                Log.d("Response desde viewmodel", clientes.toString())
                Log.d("Response", Response.body().toString())
                Log.d("Response", Response.code().toString())
                Log.d("Response", Response.message())
            } catch (e: Exception) {
                Log.d("Response", "Error al cargar clientes ${e.message}")
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}

