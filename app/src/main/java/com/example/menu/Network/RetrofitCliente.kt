package com.example.menu.Network

import com.example.menu.Network.Client.retrofitclientes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCliente {
    private const val BASE_URL = "https://farmacian-web-cxbfe7dpacbfb6c8.canadacentral-01.azurewebsites.net/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: retrofitclientes = retrofit.create(retrofitclientes::class.java)
}