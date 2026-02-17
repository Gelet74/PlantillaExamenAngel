package com.example.plantillaexamenangel.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.plantillaexamenangel.modelo.ModeloBD

@Dao
interface PlantillaDao {

    @Query("SELECT * from ModeloBD WHERE id = :id")
    suspend fun obtenerModelo(id: Int): ModeloBD

    @Query("SELECT * from ModeloBD ORDER BY nombre ASC")
    suspend fun obtenerTodosModelos(): List<ModeloBD>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(modeloDB: ModeloBD)

    @Update
    suspend fun actualizar(modeloDB: ModeloBD)

    @Delete
    suspend fun eliminar(modeloDB: ModeloBD)

}