package com.example.plantillaexamenangel.datos

import android.content.Context
import com.example.plantillaexamenangel.conexion.PlantillaBaseDatos
import com.example.plantillaexamenangel.conexion.PlantillaServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlin.getValue

interface ContenedorApp {
    val plantillaRepositorioBD : PlantillaRepositorioBD
    val plantillaRepositorioAPI : PlantillaRepositorioAPI
}

class PlantillaContenedorApp (private val context: Context) : ContenedorApp {
    private val baseUrl = "http://10.0.2.2:3000"

    private val json = Json {ignoreUnknownKeys = true}

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: PlantillaServicioApi by lazy {
        retrofit.create(PlantillaServicioApi::class.java)
    }

    override val plantillaRepositorioAPI : PlantillaRepositorioAPI by lazy {
        ConexionPlantillaRepositorioAPI(servicioRetrofit)
    }

    //parte de rom
    override val plantillaRepositorioBD: PlantillaRepositorioBD by lazy {
        ConexionPlantillaRepositorioBD(PlantillaBaseDatos.obtenerBaseDatos(context).plantillaDao())
    }



}