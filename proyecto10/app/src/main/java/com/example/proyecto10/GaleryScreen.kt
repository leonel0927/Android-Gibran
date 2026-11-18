package com.example.proyecto10

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen() {

    // Lista de imágenes alojadas en internet
    val imagenes = remember {
        listOf(
            "https://picsum.photos/id/237/600/600",
            "https://picsum.photos/id/10/600/600",
            "https://picsum.photos/id/15/600/600",
            "https://picsum.photos/id/28/600/600",
            "https://picsum.photos/id/40/600/600",
            "https://picsum.photos/id/50/600/600"
        )
    }

    // Guarda la imagen que el usuario seleccionó
    var imagenSeleccionada by remember {
        mutableStateOf<String?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Galería de imágenes")
                }
            )
        }
    ) { paddingValues ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(imagenes) { url ->

                ImagenItem(
                    url = url,
                    onClick = {
                        imagenSeleccionada = url
                    }
                )
            }
        }
    }

    // Mostrar el detalle de la imagen seleccionada
    imagenSeleccionada?.let { url ->

        AlertDialog(
            onDismissRequest = {
                imagenSeleccionada = null
            },
            title = {
                Text("Detalle de imagen")
            },
            text = {
                SubcomposeAsyncImage(
                    model = url,
                    contentDescription = "Imagen ampliada",
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        imagenSeleccionada = null
                    }
                ) {
                    Text("Cerrar")
                }
            }
        )
    }
}



@Composable
fun ImagenItem(
    url: String,
    onClick: () -> Unit
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "Imagen de la galería",

        onError = { estado ->
            android.util.Log.e(
                "GaleriaCoil",
                "Error al cargar imagen",
                estado.result.throwable
            )
        },

        contentScale = ContentScale.Crop,

        loading = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },

        error = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No se pudo cargar")
            }
        },

        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() }
    )
}
