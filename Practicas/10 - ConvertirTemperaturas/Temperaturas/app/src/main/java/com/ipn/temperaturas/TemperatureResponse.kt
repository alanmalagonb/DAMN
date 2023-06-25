package com.ipn.temperaturas

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    val body: String,
    val degrees: Double,
    val type: String)
