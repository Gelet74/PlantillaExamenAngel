package com.example.plantillaexamenangel.conexion

import com.example.plantillaexamenangel.modelo.Modelo1
import com.example.plantillaexamenangel.modelo.Modelo2
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PlantillaServicioApi {
    //modelo1

    @GET("modelo1")
    suspend fun obtenerModelo1() : List<Modelo1>

    @POST("modelo1")
    suspend fun insertarModelo1(
        @Body modelo1: Modelo1
    ) : Modelo1

    @PUT("modelo1/{id}")
    suspend fun actualizarModelo1(
        @Path("id") id:String,
        @Body modelo1: Modelo1
    ) : Modelo1

    @DELETE("modelo1/{id}")
    suspend fun borrarModelo1(
        @Path("id") id: String
    ) : Modelo1

    //modelo2

    @GET("modelo2")
    suspend fun obtenerModelo2() : List<Modelo2>

    @POST("modelo2")
    suspend fun insertarModelo2(
        @Body modelo2: Modelo2
    ) : Modelo2

    @PUT("modelo2/{id}")
    suspend fun actualizarModelo2(
        @Path("id") id:String,
        @Body modelo2: Modelo2
    ) : Modelo2

    @DELETE("modelo2/{id}")
    suspend fun borrarModelo2(
        @Path("id") id: String
    ) : Modelo2

}