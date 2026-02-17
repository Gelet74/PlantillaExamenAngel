package com.example.plantillaexamenangel.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.plantillaexamenangel.PlantillaAplicacion
import com.example.plantillaexamenangel.datos.PlantillaRepositorioAPI
import com.example.plantillaexamenangel.datos.PlantillaRepositorioBD
import com.example.plantillaexamenangel.modelo.Modelo1
import com.example.plantillaexamenangel.modelo.Modelo2
import com.example.plantillaexamenangel.modelo.ModeloBD
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PlantillaUIStateBD {
    data class ObtenerExitoTodos(val modeloBD: List<ModeloBD>) : PlantillaUIStateBD
    data class ObtenerExito(val modeloBD: ModeloBD) : PlantillaUIStateBD

    object CrearExito: PlantillaUIStateBD
    object ActualizarExito: PlantillaUIStateBD
    object EliminarExito: PlantillaUIStateBD
    object Error: PlantillaUIStateBD
    object Cargando: PlantillaUIStateBD
}

sealed interface PlantillaUIStateApi {
    data class ObtenerExitoModelo1(val modelo1: List<Modelo1>) : PlantillaUIStateApi
    data class CrearExitoModelo1(val modelo1: Modelo1) : PlantillaUIStateApi
    data class ActualizarExitoModelo1(val modelo1: Modelo1) : PlantillaUIStateApi
    data class BorrarExitoModelo1(val id: String) : PlantillaUIStateApi

    data class ObtenerExitoModelo2(val modelo2: List<Modelo2>) : PlantillaUIStateApi
    data class CrearExitoModelo2(val modelo2: Modelo2) : PlantillaUIStateApi
    data class ActualizarExitoModelo2(val modelo2: Modelo2) : PlantillaUIStateApi
    data class BorrarExitoModelo2(val id: String) : PlantillaUIStateApi

    object Error: PlantillaUIStateApi
    object Cargando: PlantillaUIStateApi

}

class PlantillaViewModel(
    private val plantillaRepositorioBD: PlantillaRepositorioBD,
    private val plantillaRepositorioAPI: PlantillaRepositorioAPI
) : ViewModel() {

    var bdUIState : PlantillaUIStateBD by mutableStateOf(PlantillaUIStateBD.Cargando)
    var apiUIState : PlantillaUIStateApi by mutableStateOf(PlantillaUIStateApi.Cargando)

    init {
        obtenerModelo1()

    }

    //parte de json
    var modelo1Pulsado : Modelo1 by mutableStateOf(Modelo1())
    var modelo2Pulsado : Modelo2 by mutableStateOf(Modelo2())

    fun actualizarModelo1Pulsado(modeloNuevo: Modelo1) {
        modelo1Pulsado = modeloNuevo
    }
    fun actualizarModelo2Pulsado(modeloNuevo: Modelo2) {
        modelo2Pulsado = modeloNuevo
    }

    //métodos

    fun obtenerModelo1() {
        viewModelScope.launch {
            apiUIState = PlantillaUIStateApi.Cargando
            apiUIState = try {
                val lista = plantillaRepositorioAPI.obtenerModelo1()
                PlantillaUIStateApi.ObtenerExitoModelo1(lista)
            } catch (e: IOException) {
                PlantillaUIStateApi.Error
            } catch (e: HttpException) {
                PlantillaUIStateApi.Error
            }
        }
    }

    fun insertarModelo1(modelo1: Modelo1) {
        viewModelScope.launch {
            apiUIState = PlantillaUIStateApi.Cargando
            apiUIState = try {
                val modeloInsertado = plantillaRepositorioAPI.insertarModelo1(modelo1)
                PlantillaUIStateApi.CrearExitoModelo1(modeloInsertado)
            } catch (e: IOException) {
                PlantillaUIStateApi.Error
            } catch (e: HttpException) {
                PlantillaUIStateApi.Error
            }
        }
    }

    fun actualizarModelo1(modelo1: Modelo1) {
        viewModelScope.launch {
            val id = modelo1.id
            apiUIState = PlantillaUIStateApi.Cargando
            apiUIState = try {
                val modeloActualizado = plantillaRepositorioAPI.actualizarModelo1(id,modelo1)
                PlantillaUIStateApi.ActualizarExitoModelo1(modeloActualizado)
            } catch (e: IOException) {
                PlantillaUIStateApi.Error
            } catch (e: HttpException) {
                PlantillaUIStateApi.Error
            }
        }
    }

    fun eliminarModelo1(id:String) {
        viewModelScope.launch {
            apiUIState = PlantillaUIStateApi.Cargando
            apiUIState = try {
                plantillaRepositorioAPI.borrarModelo1(id)
                PlantillaUIStateApi.BorrarExitoModelo1(id)
            } catch (e: IOException) {
                PlantillaUIStateApi.Error
            } catch (e: HttpException) {
                PlantillaUIStateApi.Error
            }
        }
    }

    //parte de rom
    var modeloBDPulsado: ModeloBD by mutableStateOf(ModeloBD())

    fun actualizarModeloDBPulsado(modeloNuevo: ModeloBD) {
        modeloBDPulsado = modeloNuevo
    }

    fun obtenerModelosDB() {
        viewModelScope.launch {
            bdUIState = PlantillaUIStateBD.Cargando
            bdUIState = try {
                val lista = plantillaRepositorioBD.obtenerTodosModelos()
                PlantillaUIStateBD.ObtenerExitoTodos(lista)
            } catch (e: Exception) {
                PlantillaUIStateBD.Error
            }
        }
    }

    fun obtenerModelosBD(id:Int) {
        viewModelScope.launch {
            bdUIState = PlantillaUIStateBD.Cargando
            bdUIState = try {
                val modeloBD = plantillaRepositorioBD.obtenerModelo(id)
                PlantillaUIStateBD.ObtenerExito(modeloBD)
            } catch (e: Exception) {
                PlantillaUIStateBD.Error
            }
        }
    }

    fun insertarModeloBD(modeloBD: ModeloBD) {
        viewModelScope.launch {
            bdUIState = try {
                plantillaRepositorioBD.insertar(modeloBD)
                PlantillaUIStateBD.CrearExito
            } catch (e: Exception) {
                PlantillaUIStateBD.Error
            }
        }
    }

    fun actualizarModeloBD(modeloBD: ModeloBD) {
        viewModelScope.launch {
            bdUIState = try {
                plantillaRepositorioBD.actualizar(modeloBD)
                PlantillaUIStateBD.ActualizarExito
            } catch (e: Exception) {
                PlantillaUIStateBD.Error
            }
        }
    }

    fun eliminarModeloBD(modeloBD: ModeloBD) {
        viewModelScope.launch {
            bdUIState = try {
                plantillaRepositorioBD.eliminar(modeloBD)
                PlantillaUIStateBD.EliminarExito
            } catch (e: Exception) {
                PlantillaUIStateBD.Error
            }
        }
    }


    //factory
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as PlantillaAplicacion)
                val plantillaRepositorioBD = aplicacion.contenedor.plantillaRepositorioBD
                val plantillaRepositorioAPI = aplicacion.contenedor.plantillaRepositorioAPI
                PlantillaViewModel(plantillaRepositorioBD = plantillaRepositorioBD, plantillaRepositorioAPI = plantillaRepositorioAPI)
            }
        }
    }
}