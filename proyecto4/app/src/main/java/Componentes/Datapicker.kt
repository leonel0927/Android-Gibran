package Componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import androidx.compose.ui.graphics.Color

@Composable
fun CustomDatapicker(){
    var openDialog by remember {mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var SelectDate by remember { mutableStateOf("Selecciona una fecha ") }
    Button(
        onClick = {openDialog=true},
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(SelectDate)
    }
    if (openDialog){
        DatePickerDialog (
            onDismissRequest = {openDialog=false},
            confirmButton = {
                TextButton (
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            formato.timeZone = TimeZone.getTimeZone("UTC")
                            SelectDate=formato.format(Date(millis))
                        }
                        openDialog=false
                    })
                {
                    Text("Aceptar", color = Color.Blue)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {openDialog=false}
                ) {
                    Text("Cancelar", color = Color.Red)
                }
            }
        ){
            DatePicker(state = datePickerState)
        }
    }
}