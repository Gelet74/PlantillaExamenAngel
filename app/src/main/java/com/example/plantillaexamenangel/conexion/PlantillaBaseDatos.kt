package com.example.plantillaexamenangel.conexion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.plantillaexamenangel.dao.PlantillaDao
import com.example.plantillaexamenangel.modelo.ModeloBD

@Database(entities = [ModeloBD::class], version = 1, exportSchema = false)
abstract class PlantillaBaseDatos : RoomDatabase() {

    abstract  fun plantillaDao() : PlantillaDao

    companion object {
        @Volatile
        private var Instance: PlantillaBaseDatos? = null

        fun obtenerBaseDatos(context: Context): PlantillaBaseDatos {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PlantillaBaseDatos::class.java, "plantilladb")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}