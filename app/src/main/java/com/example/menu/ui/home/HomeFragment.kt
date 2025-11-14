package com.example.menu.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.menu.Models.VentasResponse
import com.example.menu.ViewModel.ClienteviewModel
import com.example.menu.databinding.FragmentHomeBinding
import kotlin.getValue

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    @SuppressLint("UnrememberedMutableState")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView: ComposeView = binding.composeView

        composeView.apply {
            setContent {
                var clientes by mutableStateOf<List<VentasResponse>>(emptyList())
                val clienteViewModel: ClienteviewModel by viewModels()
                clienteViewModel.run {
                    cargarClientes(requireContext())
                }
                clientes = clienteViewModel.clientes
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        "Dashboard Screen",
                        color = Color.Blue.copy(0.6f),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    LazyColumn {
                        items(clientes.size) { index ->
                            val cliente = clientes
                            Text(text = "Cliente: ${cliente[index].Cliente} - Fecha: ${cliente[index].Fecha}")
                        }
                    }
                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}