package com.example.mini_proyecto

import Componentes.ElegirCarrera
import Componentes.ElegirEstatus
import Componentes.ElegirTurno
import android.widget.Toast
import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.mini_proyecto.data.preferencias
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController:NavController ) {
    var Matricula by remember { mutableStateOf("") }
    var apellido1 by remember { mutableStateOf("") }
    var apellido2 by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf(false) }
    var turno by remember { mutableStateOf("") }
    val context= LocalContext.current
    val preferencias = remember { preferencias(context) }
    LaunchedEffect(Unit) {
        Matricula=preferencias.getMatricula()
        apellido1=preferencias.getApellido1()
        apellido2=preferencias.getApellido2()
        nombre=preferencias.getNombre()
        turno=preferencias.getTurno()
        carrera=preferencias.getCarrera()
        activo= preferencias.getActivo()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MINI PROYECTO", fontSize = 24.sp, color = Color.Black, modifier = Modifier.padding(top = 37.dp))
        HorizontalDivider()
        OutlinedTextField(
            value = Matricula,
            onValueChange = { Matricula = it },
            label = { Text("Matricula") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
            )
        OutlinedTextField(
            value = apellido1,
            onValueChange = { apellido1 = it },
            label = { Text("Apellido 1") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        OutlinedTextField(
            value = apellido2,
            onValueChange = { apellido2 = it },
            label = { Text("Apellido 2") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombres") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        ElegirCarrera(
            carreraSeleccionada = carrera,
            onCarreraChange = { carrera = it }
        )

        ElegirEstatus(
            estatusSeleccionado = activo,
            onEstatusChange = { activo = it }
        )

        ElegirTurno(
            turnoSeleccionado = turno,
            onTurnoChange = { turno = it }
        )
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    preferencias.saveData(
                        Nombre = nombre,
                        AP1 = apellido1,
                        AP2=apellido2,
                        Matricula = Matricula,
                        Carrea = carrera,
                        Turno = turno,
                        Activo = activo
                    )
                    Toast.makeText(context, "Configuracion guardada", Toast.LENGTH_SHORT)
                        .show()
                },
            ) {
                Text("Guardar datos")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = {
                        preferencias.saveData(
                            Nombre = nombre,
                            AP1 = apellido1,
                            AP2=apellido2,
                            Matricula = Matricula,
                            Carrea = carrera,
                            Turno = turno,
                            Activo = activo
                        )
                    navController.navigate("second_screen")
                }
            ) {
                Text("Ver datos guardados")
            }
        }
    }
}