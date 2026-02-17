package com.example.plantillaexamenangel.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Modelo1(
    @SerialName(value = "id")
    val id: String = "",
    @SerialName(value = "campo2")
    val campo2: String = "",
    @SerialName("lista")
    val lista: List<Modelo2> = listOf<Modelo2>()
)