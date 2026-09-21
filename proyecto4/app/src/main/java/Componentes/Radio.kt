package Componentes
import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.Color

@Composable
fun CustomRadio(){
    var eleccionRadio by remember { mutableStateOf("Opcion 1") }
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (eleccionRadio=="Opcion 1"),
            onClick = {eleccionRadio="Opcion 1"}
        )
        Text(
            text = "Opcion 1",
            color = Color.White,
            modifier = Modifier.clickable{eleccionRadio="Opcion 1"}
        )
        Spacer(modifier = Modifier.width(16.dp))
        RadioButton(
            selected = (eleccionRadio=="Opcion 2"),
            onClick = {eleccionRadio ="Opcion 2"}
        )
        Text(
            text = "Opcion 2",
            color = Color.White,
            modifier = Modifier.clickable{eleccionRadio="Opcion 2"}
        )
    }

}