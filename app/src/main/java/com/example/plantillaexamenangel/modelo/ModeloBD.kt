package com.example.plantillaexamenangel.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ModeloBD")
data class ModeloBD(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String = "",
    val precio: Double = 0.0,
    val cantidad: Int = 0
)