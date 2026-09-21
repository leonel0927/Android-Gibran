package Componentes

import android.widget.Switch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun CustomSwitch(){
    var isCheck by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Seleccine la opcion mamahuevo ", fontSize = 16.sp, color = Color.White)
        Switch(
            checked = isCheck,
            onCheckedChange = {isCheck=it}
        )
    }
}