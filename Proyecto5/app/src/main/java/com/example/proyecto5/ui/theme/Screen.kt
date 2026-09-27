package com.example.proyecto5.ui.theme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto5.data.PreferencesManager

@Composable
fun Screen() {
    var tema by remember { mutableStateOf(false) }
    var notificaciones by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    val context = LocalContext.current
    val preferencemanager = remember { PreferencesManager(context) }
    LaunchedEffect(Unit)
    {
        nombre = preferencemanager.getUser()
        notificaciones = preferencemanager.getNotification()
        tema = preferencemanager.getTema()
    }
    val ColorScheme = if (tema) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(colorScheme = ColorScheme) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Practica 5: Configuracion", fontSize = 24.sp, modifier = Modifier.padding(vertical = 14.dp))
                HorizontalDivider()
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Recibir notificaciones ", fontSize = 16.sp)
                    Switch(
                        checked = notificaciones,
                        onCheckedChange = { notificaciones = it }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Activar modo oscuro ", fontSize = 16.sp)
                    Switch(
                        checked = tema,
                        onCheckedChange = { tema = it }
                    )
                }
                HorizontalDivider()
                Button(
                    onClick = {
                        preferencemanager.saveSettings(
                            User = nombre,
                            notificacion = notificaciones,
                            tema = tema
                        )
                        Toast.makeText(context, "Configuracion guardada", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar configuracion", fontSize = 16.sp)
                }
                OutlinedButton(
                    onClick = {
                        nombre = preferencemanager.getUser()
                        notificaciones = preferencemanager.getNotification()
                        tema = preferencemanager.getTema()
                        Toast.makeText(context, "Configuracion recargada", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Recargar datos guardados", fontSize = 16.sp)
                }
                TextButton(
                    onClick = {
                        preferencemanager.clear()
                        nombre = ""
                        notificaciones = false
                        tema = false
                        Toast.makeText(context, "Configuracion eliminada", Toast.LENGTH_SHORT)
                            .show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Eliminar configuracion", fontSize = 16.sp, color = Color.Red)
                }
            }
        }
    }
}