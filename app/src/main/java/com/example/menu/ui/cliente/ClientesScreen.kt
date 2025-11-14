package com.example.menu.ui.cliente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.menu.Models.DimCliente
import com.example.menu.ViewModel.ClienteviewModel

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ClientesScreen(viewModel: ClienteviewModel = viewModel()) {
//
//    val clientes by viewModel.clientes.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.listClient()
//    }
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        items(clientes) { cliente ->
//            ClienteCard(cliente)
//        }
//    }
//}

@Composable
fun ClienteCard(c: DimCliente) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("ID: ${c.clienteKey}")
            Text("Nombre: ${c.nombres} ${c.apellido1} ${c.apellido2}")
            Text("Teléfono: ${c.telefono}")
        }
    }
}

