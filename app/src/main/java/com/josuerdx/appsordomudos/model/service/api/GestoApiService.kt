package com.josuerdx.appsordomudos.model.service.api

import com.josuerdx.appsordomudos.model.Gesto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Body

// Servicio de la API
interface GestoApiService {
    @GET("gestos/")
    suspend fun getGestos(): Response<List<Gesto>>

    @GET("gestos/{id}/")
    suspend fun getGesto(@Path("id") id: Int): Response<Gesto>

    @PUT("gestos/{id}/")
    suspend fun updateGesto(@Path("id") id: Int, @Body gesto: Gesto): Response<Gesto>
}
