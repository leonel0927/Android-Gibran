package Componentes

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun CustomCheck(){
    var isCheck by remember{mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically){
        Checkbox(
            checked = isCheck,
            onCheckedChange = {isCheck=it}
        )
        Text("Acepto Terminos y condiciones", color = Color.White)
    }
}