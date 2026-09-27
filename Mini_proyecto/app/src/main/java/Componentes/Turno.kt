    package Componentes

    import android.widget.Space
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.material3.RadioButton
    import androidx.compose.material3.Text
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.mini_proyecto.data.preferencias

    @Composable
    fun ElegirTurno(
        turnoSeleccionado: String,
        onTurnoChange: (String) -> Unit
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = (turnoSeleccionado == "Matutino"),
                onClick = { onTurnoChange("Matutino") }
            )
            Text("Matutino", modifier = Modifier.clickable { onTurnoChange("Matutino") })
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = (turnoSeleccionado == "Vespertino"),
                onClick = { onTurnoChange("Vespertino") }
            )
            Text("Vespertino", modifier = Modifier.clickable { onTurnoChange("Vespertino") })
        }
    }