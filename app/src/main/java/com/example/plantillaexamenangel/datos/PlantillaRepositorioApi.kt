package com.example.plantillaexamenangel.datos

import com.example.plantillaexamenangel.conexion.PlantillaServicioApi
import com.example.plantillaexamenangel.modelo.Modelo1
import com.example.plantillaexamenangel.modelo.Modelo2

interface PlantillaRepositorioAPI {
    //modelo1

    suspend fun obtenerModelo1() : List<Modelo1>
    suspend fun insertarModelo1(modelo1: Modelo1) : Modelo1
    suspend fun actualizarModelo1(id: String,modelo1: Modelo1) : Modelo1
    suspend fun borrarModelo1(id: String) : Modelo1

    //modelo2
    suspend fun obtenerModelo2() : List<Modelo2>
    suspend fun insertarModelo2(modelo2: Modelo2) : Modelo2
    suspend fun actualizarModelo2(id: String,modelo2: Modelo2) : Modelo2
    suspend fun borrarModelo2(id: String) : Modelo2
}

class ConexionPlantillaRepositorioAPI(
    private val plantillaServicioAPI: PlantillaServicioApi
) : PlantillaRepositorioAPI {

    //modelo1
    override suspend fun obtenerModelo1() : List<Modelo1> = plantillaServicioAPI.obtenerModelo1()
    override suspend fun insertarModelo1(modelo1: Modelo1) : Modelo1 = plantillaServicioAPI.insertarModelo1(modelo1)
    override suspend fun actualizarModelo1(id: String,modelo1: Modelo1) : Modelo1 = plantillaServicioAPI.actualizarModelo1(id,modelo1)
    override suspend fun borrarModelo1(id: String) : Modelo1 = plantillaServicioAPI.borrarModelo1(id)

    //modelo2
    override suspend fun obtenerModelo2() : List<Modelo2> = plantillaServicioAPI.obtenerModelo2()
    override suspend fun insertarModelo2(modelo2: Modelo2) : Modelo2 = plantillaServicioAPI.insertarModelo2(modelo2)
    override suspend fun actualizarModelo2(id: String,modelo2: Modelo2) : Modelo2 = plantillaServicioAPI.actualizarModelo2(id,modelo2)
    override suspend fun borrarModelo2(id: String) : Modelo2 = plantillaServicioAPI.borrarModelo2(id)




}