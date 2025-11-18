package com.example.menu.ui.Productos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.menu.R
import com.example.menu.databinding.ActivityFragmentMainProductosBinding

class fragmentMainProductos : Fragment() {

    private lateinit var binding : ActivityFragmentMainProductosBinding
    private lateinit var mContext : Context


    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = ActivityFragmentMainProductosBinding.inflate(inflater, container, false)


        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.op_mis_productos ->{
                    replaceFragment(fragmentProductos())
                }
                R.id.op_mis_ordenes ->{
                    replaceFragment(fragmentMisPedidos())
                }
            }
            true
        }

        replaceFragment(fragmentProductos())
        binding.bottomNavigation.selectedItemId = R.id.op_mis_productos

        binding.addFab.setOnClickListener {
            Toast.makeText(
                mContext,
                "Has presionado en el botón flotante",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root

    }

    private fun replaceFragment(fragment: Fragment){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        binding = null
    }
}