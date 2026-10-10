package com.example.proyecto8.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.proyecto8.data.Usuario
import com.example.proyecto8.domain.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val context = LocalContext.current

    // Obtenemos la instancia de la BD y el DAO
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.usuarioDao()

    // Variable para ejecutar las corrutinas (inserciones)
    val coroutineScope = rememberCoroutineScope()

    // collectAsState() transforma el Flow en un Estado que redibuja la UI si hay cambios
    val usuarios by dao.obtenerTodos().collectAsState(initial = emptyList())
    var nuevoNombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp)
    ) {
        OutlinedTextField(
            value = nuevoNombre,
            onValueChange = { nuevoNombre = it },
            label = { Text("Nombre del usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (nuevoNombre.isNotBlank()) {
                    coroutineScope.launch(Dispatchers.IO) {
                        // 1. Guardar en la base de datos (Hilo secundario)
                        dao.insertar(Usuario(nombre = nuevoNombre))
                    }
                    // 2. Regresar al hilo principal para actualizar la UI
                    coroutineScope.launch(Dispatchers.Main) {
                        nuevoNombre = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Usuario")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lista de Usuarios Registrados:", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(usuarios) { usuario ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "ID: ${usuario.id} | Nombre: ${usuario.nombre}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}