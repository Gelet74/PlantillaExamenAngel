package com.example.plantillaexamenangel.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Modelo2(
    @SerialName(value = "id")
    val id: String = "",
    @SerialName(value = "contenido")
    val contenido : String = ""
)