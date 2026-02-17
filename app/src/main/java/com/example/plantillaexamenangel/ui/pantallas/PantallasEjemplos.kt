package com.example.plantillaexamenangel.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantillaexamenangel.modelo.Modelo1
import com.example.plantillaexamenangel.modelo.Modelo2


@Composable
fun PantallaInsertarEjemplo(
    onInsertarPulsado: (Modelo1) -> Unit,
    modifier: Modifier = Modifier
) {

    //La pantalla de actualizar es lo mismo, solo que el valor original de estas
    // variables son los campos del objeto que se pasaría por parámetro a pantalla actualizar
    var campo2 by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Insertar modelo1", fontSize = 20.sp,modifier = Modifier.padding(vertical = 10.dp))

        TextField(
            value = campo2,
            label = {Text("Campo2")},
            onValueChange = {campo2 = it},
            modifier = Modifier.padding(vertical = 10.dp)
        )

        TextField(
            value = precio.toString(),
            label = {Text("precio")},
            onValueChange = {precio = it.toDouble()},
            modifier = Modifier.padding(vertical = 10.dp),

            )

        Button(
            onClick = {
                val productoNuevo = Modelo1(campo2 = campo2, lista = listOf<Modelo2>())
                onInsertarPulsado(productoNuevo)
            }
        ) {
            Text("Insertar")
        }
    }


}