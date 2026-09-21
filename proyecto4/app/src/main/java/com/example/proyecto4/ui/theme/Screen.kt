package com.example.proyecto4.ui.theme

import Componentes.CustomCheck
import Componentes.CustomDatapicker
import Componentes.CustomRadio
import Componentes.CustomSpinner
import Componentes.CustomSwitch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
@Composable
fun Screen() {
    Scaffold(
        containerColor = Color.Black
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Practica 4: Componentes avanzados",
                fontSize = 22.sp,
                color = Color.White
            )
            HorizontalDivider()
            Text("1.- ChechBox", color = Color.Red)
            CustomCheck()
            HorizontalDivider()
            Text("2.-RadioButton", color = Color.Cyan)
            CustomRadio()
            HorizontalDivider()
            Text("3.- Calendario", color = Color.Magenta)
            CustomDatapicker()
            HorizontalDivider()
            Text("4.- Combobox Compañero (Spinner)", color = Color.Green)
            CustomSpinner()
            HorizontalDivider()
            Text("5.-Swicth y la de nintendo", color = Color.Blue)
            CustomSwitch()
        }

    }
}