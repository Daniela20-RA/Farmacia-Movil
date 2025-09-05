package com.example.menu.ui.Cliente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClienteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Cliente Fragment"
    }
    val text: LiveData<String> = _text
}