package com.example.plantillaexamenangel.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.plantillaexamenangel.modelo.Modelo1
import com.example.plantillaexamenangel.ui.PlantillaUIStateApi
import com.example.plantillaexamenangel.R


@Composable
fun PantallaInicio(
    apiUIState: PlantillaUIStateApi,
    onModelosObtenido: () -> Unit,
    onModelo1Pulsado: (Modelo1) -> Unit,
    modifier: Modifier = Modifier
) {


    when (apiUIState) {
        is PlantillaUIStateApi.Cargando -> PantallaCargando()
        is PlantillaUIStateApi.Error -> PantallaError()

        is PlantillaUIStateApi.ObtenerExitoModelo1 -> PantallaListarModelos(
            lista = apiUIState.modelo1,
            onModelo1Pulsado = onModelo1Pulsado,
            modifier = modifier.fillMaxWidth()
        )
        is PlantillaUIStateApi.CrearExitoModelo1 -> onModelosObtenido()
        is PlantillaUIStateApi.ActualizarExitoModelo1 -> onModelosObtenido()

        else -> Text("PANTALLA DE INICIO HGOLA")
    }

}

@Composable
fun PantallaListarModelos(
    lista : List<Modelo1>,
    onModelo1Pulsado: (Modelo1) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(lista){ modelo ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(
                        onClick = { onModelo1Pulsado(modelo) }
                    )
            ){
                Column(
                    modifier= Modifier.fillMaxWidth()
                ){

                }
            }
        }
    }
}

@Composable
fun PantallaCargando(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.cargando),
        contentDescription = "Cargando"
    )
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.error),
        contentDescription = "Error"
    )
}