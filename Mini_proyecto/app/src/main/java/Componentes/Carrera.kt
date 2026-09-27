package Componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun ElegirCarrera(
    carreraSeleccionada: String,
    onCarreraChange: (String) -> Unit
) {
    var extender by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = if (carreraSeleccionada.isEmpty()) "Seleccione carrera" else carreraSeleccionada,
            onValueChange = {},
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            trailingIcon = {
                IconButton(onClick = { extender = true }) {
                    Icon(Icons.Default.ArrowDownward, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            expanded = extender,
            onDismissRequest = { extender = false }
        ) {
            DropdownMenuItem(
                text = { Text("ING software") },
                onClick = {
                    onCarreraChange("ING software")
                    extender = false
                }
            )
            DropdownMenuItem(
                text = { Text("ING civil") },
                onClick = {
                    onCarreraChange("ING civil")
                    extender = false
                }
            )
            DropdownMenuItem(
                text = { Text("ING geo") },
                onClick = {
                    onCarreraChange("ING geo")
                    extender = false
                }
            )
        }
    }
}