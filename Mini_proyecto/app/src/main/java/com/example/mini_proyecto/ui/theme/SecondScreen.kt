package com.example.proyecto3.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mini_proyecto.data.preferencias

@Composable
fun secondScree(){
    val context = LocalContext.current
    val pref= remember { preferencias(context) }
    val nombre = pref.getNombre()
    val AP1 = pref.getApellido1()
    val AP2 = pref.getApellido2()
    val Matricula = pref.getMatricula()
    val Carrera = pref.getCarrera()
    val Turno = pref.getTurno()
    val Estatus = pref.getActivo()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Perfil de usuario",
                fontSize= 24.sp
            )
            Card(
                modifier= Modifier.fillMaxWidth(),

            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(text = "Nombre: ")
                    Text(text = "$AP1 $AP2 $nombre")
                    Text(text = "Numero de cuenta: ")
                    Text(text = Matricula)
                    Text(text = "Carrera: ")
                    Text(text = Carrera)
                    Text(text = "Estatus: ")
                    Text(if (Estatus) "Activo" else "Inactivo")
                    Text(text = "Turno: ")
                    Text(text = Turno)
                }
            }
        }
    }
}