package com.example.menu.ui.Cliente
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.menu.databinding.FragmentClienteBinding
import com.example.menu.clienteViewModel
import android.widget.Toast
import com.example.menu.client

class ClienteFragment : Fragment() {

    private var _binding: FragmentClienteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:clienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClienteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(clienteViewModel::class.java)

        // Observa mensajes de resultado
        viewModel.mensaje.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        binding.iniciarbutton.setOnClickListener {
            val cliente = client(
                id = 0,
                codigo = "",
                Nombres = binding.editTextnombre.text.toString(),
                primerApellido = binding.editTextapellido1.text.toString(),
                segundoApellido = binding.editapellido2.text.toString(),
                direccion = binding.editdireccion.text.toString(),
                telefono = binding.edittelefono.text.toString()
            )
            viewModel.createCliente(cliente)
        }

        return binding.root
    }
}