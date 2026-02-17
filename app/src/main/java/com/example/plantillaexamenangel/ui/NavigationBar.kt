package com.example.plantillaexamenangel.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlusOne
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.plantillaexamenangel.R
import com.example.plantillaexamenangel.modelo.Ruta
import com.example.plantillaexamenangel.ui.pantallas.PantallaFavoritos
import com.example.plantillaexamenangel.ui.pantallas.PantallaInicio
import com.example.plantillaexamenangel.ui.pantallas.PantallaInsertar

enum class PantallasBar(@StringRes val titulo: Int) {
    Inicio(titulo = R.string.inicio),
    Favoritos(titulo = R.string.favoritos),
    Insertar(titulo = R.string.insertar)
}

val listaRutas = listOf(
    Ruta(PantallasBar.Inicio.titulo, PantallasBar.Inicio.name, Icons.Filled.Home,Icons.Outlined.Home),
    Ruta(PantallasBar.Favoritos.titulo, PantallasBar.Favoritos.name, Icons.Filled.Star,Icons.Outlined.Star),
    Ruta(PantallasBar.Insertar.titulo, PantallasBar.Insertar.name, Icons.Filled.PlusOne,Icons.Outlined.PlusOne),
)

@Composable
fun NavigationBar(
    viewModel: PlantillaViewModel = viewModel(factory = PlantillaViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                listaRutas.forEachIndexed { indice, ruta ->
                    NavigationBarItem(
                        icon = {
                            if (selectedItem == indice)
                                Icon(
                                    imageVector = ruta.iconoLleno,
                                    contentDescription = stringResource(id = ruta.nombre)
                                )
                            else
                                Icon(
                                    imageVector = ruta.iconoVacio,
                                    contentDescription = stringResource(id = ruta.nombre)
                                )
                        },
                        label = { Text(stringResource(id = ruta.nombre)) },
                        selected = selectedItem == indice,
                        onClick = {


                            if (indice == 1) {
                                //if indice es igual al de la pantalla que toca hacer algo cuando se le de al botón

                            }

                            selectedItem = indice
                            navController.navigate(ruta.ruta)
                        }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val bdUIState = viewModel.bdUIState
        val apiUIState = viewModel.apiUIState

        NavHost(
            navController = navController,
            startDestination = PantallasBar.Inicio.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Grafo de las rutas
            composable(route = PantallasBar.Inicio.name) {
                PantallaInicio(
                    apiUIState = apiUIState,
                    modifier = Modifier
                        .fillMaxSize(),
                    onModelo1Pulsado = {},
                    onModelosObtenido = {}
                )
            }
            composable(route = PantallasBar.Favoritos.name) {
                PantallaFavoritos(
                    modifier = Modifier
                        .fillMaxSize(),
                    bdUiState = bdUIState
                )
            }
            composable(route = PantallasBar.Insertar.name) {
                PantallaInsertar(
                    onInsertarPulsado = {
                        //evento del viewmodel
                        navController.popBackStack(PantallasDrawer.Inicio.name, inclusive = false)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}