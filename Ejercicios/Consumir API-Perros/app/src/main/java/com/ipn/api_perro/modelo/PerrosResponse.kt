package com.ipn.api_perro.modelo

import com.google.gson.annotations.SerializedName

data class PerrosResponse(
    // El valor de Serialized Name
    // debe ser igual a atributo de respuesta del API
    @SerializedName("status")
    var status : String,
    @SerializedName("message")
    var images: List<String>
)
