package Componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color

@Composable
fun CustomSpinner(){
 var extender by remember { mutableStateOf(false)}
 var elementos by remember { mutableStateOf("Seleccione una opcion") }
    Box(modifier = Modifier.fillMaxWidth()){
        OutlinedTextField(
            value = elementos,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = {extender=true}) {
                    Icon(Icons.Default.ArrowDownward, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            expanded = extender,
            onDismissRequest = {extender=false}
        ) {
            DropdownMenuItem(
            text = {Text("Elemento A", color = Color.White)},
                onClick = {
                    elementos="Elemento A"
                    extender=false
                }
            )
            DropdownMenuItem(
                text = {Text("Elemento B", color = Color.White)},
                onClick = {
                    elementos="Elemento B"
                    extender=false
                }
            )
        }
    }
}