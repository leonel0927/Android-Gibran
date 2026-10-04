package com.example.proyecto7.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val context = LocalContext.current
    var isfavorite by remember { mutableStateOf(false) }
    var menuextentido by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Mi aplicacion mamalona")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Green,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context,"Click navegacion", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Default.MenuOpen, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        isfavorite=!isfavorite
                            val mensaje=if (isfavorite) "Añadido a favoritos" else  "Eliminado de favoritos"
                            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = if (isfavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = if (isfavorite) "Añadir a favorito" else "Eliminar de favoritos",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = {menuextentido=true}
                    ) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Mas")
                    }
                    DropdownMenu(
                        expanded = menuextentido,
                        onDismissRequest = {menuextentido=false}
                    ) {
                        DropdownMenuItem(
                            text = {Text("configuracion")},
                            onClick = {
                                menuextentido=false
                                Toast.makeText(context,"Configuracion Seleccionada", Toast.LENGTH_SHORT).show()
                            }
                        )
                        DropdownMenuItem(
                            text = {Text("acerca de")},
                            onClick = {
                                menuextentido=false
                                Toast.makeText(context,"Acerca de seleccionada", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            )
        }
    ) { innerPaddin ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddin)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienveido al material design",
                fontSize = 32.sp
            )
            Text(
                text = "explora mamahuevo",
                fontSize = 16.sp
            )
        }
    }
}