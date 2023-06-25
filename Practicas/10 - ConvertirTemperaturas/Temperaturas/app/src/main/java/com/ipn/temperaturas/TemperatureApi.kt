package com.ipn.temperaturas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TemperatureApi {

    @GET("temperature")
    fun convertTemperature(
        @Query("degrees") degrees: Double,
        @Query("type") type: Char,
        @Query("typeTo") typeTo: Char
    ): Call<TemperatureResponse>

}
