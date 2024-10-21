package com.josuerdx.appsordomudos.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming

interface ApiService {
    @GET("mqtt-to-tts/")
    @Streaming
    suspend fun downloadAudio(): Response<ResponseBody>
}