package com.example.menu.Network

import com.example.menu.Network.Client.retrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://0khzzbmk-44347.use.devtunnels.ms/"   // CAMBIA ESTO

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: retrofitClient = retrofit.create(retrofitClient::class.java)
}





