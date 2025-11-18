package com.example.menu.ui.cliente
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
<<<<<<< HEAD:app/src/main/java/com/example/menu/ui/Cliente/ClienteFragment.kt
=======


>>>>>>> 3bf93b747f7bb7276f2bb365e2fbadd102817a69:app/src/main/java/com/example/menu/ui/cliente/ClienteFragment.kt
        _binding = FragmentClienteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(clienteViewModel::class.java)

<<<<<<< HEAD:app/src/main/java/com/example/menu/ui/Cliente/ClienteFragment.kt
        // Observa mensajes de resultado
        viewModel.mensaje.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
=======
        return root
    }
>>>>>>> 3bf93b747f7bb7276f2bb365e2fbadd102817a69:app/src/main/java/com/example/menu/ui/cliente/ClienteFragment.kt

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