package com.ipn.api_cats.servicio

import com.ipn.api_cats.modelo.GatosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun obtenerGatosPorRazas(@Url url: String): Response<List<GatosResponse>>
}