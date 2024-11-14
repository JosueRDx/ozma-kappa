package com.josuerdx.appsordomudos.model.service.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GestoApiClient {
    private const val BASE_URL = "http://192.168.196.205:8000/api/"

    val service: GestoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GestoApiService::class.java)
    }
}
