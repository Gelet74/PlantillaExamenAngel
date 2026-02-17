package com.example.plantillaexamenangel.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerMostrado(
    onConfirm: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirm(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerMostrado(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit
) {
    val horaActual = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = horaActual.get(Calendar.HOUR_OF_DAY),
        initialMinute = horaActual.get(Calendar.MINUTE),
        is24Hour = true
    )
    var mostrarDialogo by remember { mutableStateOf(true) }

    if (mostrarDialogo) {
        AlertDialog(
            text = {
                Column {
                    TimeInput(state = timePickerState)
                    //TimePicker(state = timePickerState)
                }
            },
            onDismissRequest = {
                onDismiss()
                mostrarDialogo = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm(timePickerState)
                        mostrarDialogo = false
                    }
                ) {
                    Text(text = "aceptar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss()
                        mostrarDialogo = false
                    }
                ) {
                    Text(text = "cancelar")
                }
            }
        )
    }
}