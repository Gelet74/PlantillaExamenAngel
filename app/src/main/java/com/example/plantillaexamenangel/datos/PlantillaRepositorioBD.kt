package com.example.plantillaexamenangel.datos

import com.example.plantillaexamenangel.dao.PlantillaDao
import com.example.plantillaexamenangel.modelo.ModeloBD

interface PlantillaRepositorioBD {
    suspend fun obtenerModelo(id: Int): ModeloBD
    suspend fun obtenerTodosModelos(): List<ModeloBD>
    suspend fun insertar(modelobd: ModeloBD)
    suspend fun actualizar(modelobd: ModeloBD)
    suspend fun eliminar(modelobd: ModeloBD)
}

class ConexionPlantillaRepositorioBD(
    private val plantillaDao: PlantillaDao
) : PlantillaRepositorioBD {
    override suspend fun obtenerModelo(id: Int): ModeloBD = plantillaDao.obtenerModelo(id)

    override suspend fun obtenerTodosModelos(): List<ModeloBD> = plantillaDao.obtenerTodosModelos()

    override suspend fun insertar(modelobd: ModeloBD) = plantillaDao.insertar(modelobd)

    override suspend fun actualizar(modelobd: ModeloBD) =  plantillaDao.actualizar(modelobd)

    override suspend fun eliminar(modelobd: ModeloBD) = plantillaDao.eliminar(modelobd)

}