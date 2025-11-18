package com.example.menu

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class clienteViewModel : ViewModel() {
//    private val api = ApiClient.create()
//
//    private val _clientes = MutableLiveData<List<client>>(emptyList())
//    val clientes: LiveData<List<client>> = _clientes
//
//    private val _mensaje = MutableLiveData<String?>()
//    val mensaje: LiveData<String?> = _mensaje
//
//    fun load() {
//        viewModelScope.launch {
//            try {
//                _clientes.value = api.getclientes()
//            } catch (e: Exception) {
//                _mensaje.value = "Error al cargar clientes .Intente Nuevamente"
//            }
//        }
//    }
//
//    fun createCliente(cliente: client) {
//        viewModelScope.launch {
//            try {
//                val nuevo = api.createCliente(cliente)
//                _mensaje.value = "Cliente creado: ${nuevo.Nombres}"
//                // refresca la lista
//                load()
//            } catch (e: Exception) {
//                _mensaje.value = "Error al crear cliente .Intente Nuevamente"
//            }
//        }
//    }
}
