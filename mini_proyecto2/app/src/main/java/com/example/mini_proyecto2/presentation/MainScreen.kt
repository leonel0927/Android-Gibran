package com.example.mini_proyecto2.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mini_proyecto2.data.Tarea
import com.example.mini_proyecto2.domain.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.tareaDao()
    val coroutineScope = rememberCoroutineScope()

    val tareas by dao.obtenerTodas().collectAsState(initial = emptyList())
    var nuevoTitulo by remember { mutableStateOf("") }

    // Estado para el menú desplegable de la TopAppBar
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas - Miniproyecto 02") },
                actions = {
                    IconButton(onClick = { menuExpanded = !menuExpanded }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Acerca de") },
                            onClick = { menuExpanded = false }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Campo para nueva tarea
            OutlinedTextField(
                value = nuevoTitulo,
                onValueChange = { nuevoTitulo = it },
                label = { Text("Escribe una nueva tarea") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val textoAProcesar = nuevoTitulo.trim()
                    if (textoAProcesar.isNotEmpty()) {
                        coroutineScope.launch(Dispatchers.IO) {
                            dao.insertar(Tarea(titulo = textoAProcesar))
                        }
                        nuevoTitulo = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Tarea")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Lista de Tareas:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(
                    items = tareas,
                    key = { tarea -> tarea.id } // ¡Esto soluciona el problema de sincronización visual!
                ) { tarea ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = tarea.completada,
                                    onCheckedChange = { estado ->
                                        coroutineScope.launch(Dispatchers.IO) {
                                            dao.actualizar(tarea.copy(completada = estado))
                                        }
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = tarea.titulo,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                            }

                            IconButton(onClick = {
                                coroutineScope.launch(Dispatchers.IO) {
                                    dao.eliminar(tarea)
                                }
                            }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}