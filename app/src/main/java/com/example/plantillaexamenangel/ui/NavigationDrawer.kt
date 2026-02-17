package com.example.plantillaexamenangel.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plantillaexamenangel.modelo.DrawerMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.plantillaexamenangel.R
import com.example.plantillaexamenangel.ui.pantallas.PantallaFavoritos
import com.example.plantillaexamenangel.ui.pantallas.PantallaInicio
import com.example.plantillaexamenangel.ui.pantallas.PantallaInsertar

enum class PantallasDrawer(@StringRes val titulo: Int) {
    Inicio(R.string.inicio),
    Favoritos(R.string.favoritos),
    Insertar(R.string.insertar)
}

val menu = arrayOf(
    DrawerMenu(Icons.Filled.Face, PantallasDrawer.Inicio.titulo, PantallasDrawer.Inicio.name),
    DrawerMenu(Icons.Filled.Star, PantallasDrawer.Favoritos.titulo, PantallasDrawer.Favoritos.name),
    DrawerMenu(Icons.Filled.Star, PantallasDrawer.Insertar.titulo, PantallasDrawer.Insertar.name),
)

@Composable
fun NavigationDrawer(
    viewModel: PlantillaViewModel = viewModel(factory = PlantillaViewModel.Factory),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
){


    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = PantallasDrawer.valueOf(
        pilaRetroceso?.destination?.route ?: PantallasDrawer.Inicio.name
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    menu = menu,
                    pantallaActual = pantallaActual
                ) { ruta ->
                    coroutineScope.launch {
                        drawerState.close()
                    }

                    navController.navigate(ruta)
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    pantallaActual = pantallaActual,
                    drawerState = drawerState
                )
            },
            floatingActionButton = {
                if(pantallaActual.titulo == R.string.inicio) {
                    FloatingActionButton(
                        onClick = { navController.navigate(route = PantallasDrawer.Insertar.name) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.insertar)
                        )
                    }
                }
            }
        ) { innerPadding ->
            val bdUIState = viewModel.bdUIState
            val apiUIState = viewModel.apiUIState

            NavHost(
                navController = navController,
                startDestination = PantallasDrawer.Inicio.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                // Grafo de las rutas
                composable(route = PantallasDrawer.Inicio.name) {
                    PantallaInicio(
                        apiUIState = apiUIState,
                        modifier = Modifier
                            .fillMaxSize(),
                        onModelo1Pulsado = {},
                        onModelosObtenido = {}
                    )
                }
                composable(route = PantallasDrawer.Favoritos.name) {
                    PantallaFavoritos(
                        modifier = Modifier
                            .fillMaxSize(),
                        bdUiState = bdUIState
                    )
                }
                composable(route = PantallasDrawer.Insertar.name) {
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
}

@Composable
private fun DrawerContent(
    menu: Array<DrawerMenu>,
    pantallaActual: PantallasDrawer,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menu.forEach {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = it.titulo)) },
                icon = { Icon(imageVector = it.icono, contentDescription = null) },
                selected = it.titulo == pantallaActual.titulo,
                onClick = {
                    onMenuClick(it.ruta)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: PantallasDrawer,
    drawerState: DrawerState?,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (drawerState != null) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(R.string.atr_s)
                    )
                }
            }
        },
        modifier = modifier
    )
}