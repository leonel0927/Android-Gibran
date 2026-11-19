package com.example.practica15

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessaging

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificacionScreen() {
    val context = LocalContext.current
    val idCanal = "canal_practica_15"

    var fcmToken by remember { mutableStateOf("Obteniendo Token...") }

    LaunchedEffect(Unit) {
        crearCanalNotificacion(context, idCanal)
        // Solicitar a Firebase el Token de este teléfono
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                fcmToken = task.result
                Log.d("FCM_TOKEN", "Tu token es: $fcmToken")
            } else {
                fcmToken = "Error al obtener Token (verifica google-services.json)"
                Log.e("FCM_TOKEN", "Error obteniendo token", task.exception)
            }
        }
    }

    var permisoConcedido by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    val permisoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> permisoConcedido = isGranted }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones y Push FCM") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Tu Token de Firebase:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(fcmToken, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                if (permisoConcedido) {
                    mostrarNotificacion(context, idCanal, "Fase A", "¡Notificación Local Exitosa!")
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permisoLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            }) {
                Text("Lanzar Notificación Local")
            }
        }
    }
}
