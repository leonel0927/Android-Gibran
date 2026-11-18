package com.example.proyecto9

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen() {

    val context = LocalContext.current

    var listaImagenes by remember {
        mutableStateOf(listOf<Bitmap>())
    }

    val camaraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap: Bitmap? ->

            if (bitmap != null) {
                listaImagenes = listaImagenes + bitmap
            }
        }

    val permisoCamaraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { permisoConcedido ->

            if (permisoConcedido) {
                camaraLauncher.launch(null)
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Cámara y Galería")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    val tienePermiso =
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED

                    if (tienePermiso) {
                        camaraLauncher.launch(null)
                    } else {
                        // Solicitar permiso antes de abrir la cámara
                        permisoCamaraLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tomar foto"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {

            if (listaImagenes.isEmpty()) {

                Text("No hay fotos en la galería. ¡Toma una!")

            } else {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement =
                        Arrangement.spacedBy(8.dp),
                    horizontalArrangement =
                        Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(listaImagenes) { bitmap ->

                        ImagenCapturadaItem(bitmap = bitmap)

                    }
                }
            }
        }
    }
}

@Composable
fun ImagenCapturadaItem(bitmap: Bitmap) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation =
            androidx.compose.material3.CardDefaults
                .cardElevation(defaultElevation = 4.dp)
    ) {

        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Foto capturada",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }
}
