package com.example.proyecto1.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Firstscreen(onNavigateToSecondscreen : (String) -> Unit={}){
    var texto by remember { mutableStateOf("") }
    Scaffold(
        containerColor = Color(0xFFB7D2E5)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = texto,
                onValueChange = {texto  = it },
                label = {Text ("Escribe tu texto aqui")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height((20.dp)))
            Button(
                onClick = {
                    if (texto.isNotBlank()){
                        onNavigateToSecondscreen(texto)
                    }
                }
            ) { Text("Ir a Secondscreen")}
        }
    }
}