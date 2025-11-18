package com.example.menu.Network

import com.example.menu.Network.Client.retrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    //private const val BASE_URL = "https://farmacia-web-bhgwbrfxahachxer.canadacentral-01.azurewebsites.net/"   // CAMBIA ESTO
       private const val BASE_URL = "https://0khzzbmk-44347.use.devtunnels.ms/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: retrofitClient = retrofit.create(retrofitClient::class.java)
}





