package com.ipn.api_perro.servicio

import com.ipn.api_perro.modelo.PerrosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun obtenerPerrosPorRazas(@Url url: String): Response<PerrosResponse>
}