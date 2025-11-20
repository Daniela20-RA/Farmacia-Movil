package com.example.menu.ui.cliente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.menu.Models.Cliente
import com.example.menu.ViewModel.ClienteviewModel

class ClienteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val composeView = ComposeView(requireContext())

        composeView.setContent {

            val viewModel: ClienteviewModel by viewModels()


            val listaClientes = viewModel.cliente

            // Cargar clientes al entrar
            LaunchedEffect(Unit) {
                viewModel.cargarclientes(requireContext())
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                items(listaClientes) { cliente ->
                    ClienteCard(cliente)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        return composeView
    }
}




@Composable
fun ClienteCard(cliente: Cliente) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {

        Text(
            text = "${cliente.nombres} ${cliente.primerApellido}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text("Código: ${cliente.codigo}")
        Text("Teléfono: ${cliente.telefono}")
        Text("Dirección: ${cliente.direccion}")
    }
}


