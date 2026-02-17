package com.example.plantillaexamenangel

import android.app.Application
import com.example.plantillaexamenangel.datos.ContenedorApp
import com.example.plantillaexamenangel.datos.PlantillaContenedorApp

class PlantillaAplicacion : Application() {
    lateinit var contenedor: ContenedorApp
    override fun onCreate() {
        super.onCreate()
        contenedor = PlantillaContenedorApp(this)
    }

}