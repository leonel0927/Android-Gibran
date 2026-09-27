package Componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ElegirEstatus(
    estatusSeleccionado: Boolean,
    onEstatusChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Activo: ",
            fontSize = 16.sp,
            color = Color.Black
        )
        Switch(
            checked = estatusSeleccionado,
            onCheckedChange = { onEstatusChange(it) }
        )
    }
}