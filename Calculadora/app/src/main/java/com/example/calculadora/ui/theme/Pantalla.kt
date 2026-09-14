package com.example.calculadora.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.Row
val CalculadoraBackgroundColor= Color(0xFFB388FF)
val Screen= Color(0xFF5A5A5D)
val ColorButton=Color(0xFF855DCC)
val AppColor= Color(0xFF000000)
@Composable
fun Calculadora(){
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var operador by remember { mutableStateOf("") }
    fun onButtonClick(simbolo: String){
        when(simbolo){
            "C" ->{
              numero1=""
              numero2=""
              operador=""
            }
            "<-"->{
                if (numero2.isNotEmpty()) numero2=numero2.dropLast(1)
                else if (operador.isNotEmpty()) operador=""
                else if(numero1.isNotEmpty()) numero1=numero1.dropLast(1)
            }
            "%"->{
                if(numero1.isNotEmpty() && operador.isEmpty()){
                    numero1= LogicaCal.Porcentaje(numero1)
                }
            }
            "+","-","x","/"->{
                if (numero1.isNotEmpty()) operador=simbolo
            }
            "="->{
                if (numero1.isNotEmpty() && numero2.isNotEmpty() && operador.isNotEmpty()){
                    numero1 = LogicaCal.Calcular(numero1,numero2,operador)
                    numero2=""
                    operador=""
                }

            }
            "."->{
                if (operador.isEmpty() && !numero1.contains(("."))){
                    numero1+= if(numero1.isEmpty()) "0." else "."
                }else if (operador.isNotEmpty() && !numero2.contains(".")){
                    numero2+= if(numero2.isEmpty()) "0." else "."
                }
            }
            else -> {
                if (operador.isEmpty()){
                    if (numero1.length <8) numero1+=simbolo
                }else{
                    if (numero2.length <8)numero2+= simbolo
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
        ){
        Column(
            modifier = Modifier
                .width(320.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(CalculadoraBackgroundColor)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Screen)
                    .padding(20.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                Text(
                    text= "$numero1 $operador $numero2",
                    textAlign= TextAlign.End,
                    color=Color.White,
                    fontSize=32.sp,
                    fontWeight= FontWeight.Light,
                    maxLines=2
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Calbtn("C", Modifier.weight(1f)){onButtonClick("C")}
                    Calbtn("⌫", Modifier.weight(1f)){onButtonClick("<-")}
                    Calbtn("%", Modifier.weight(1f)){onButtonClick("%")}
                    Calbtn("/", Modifier.weight(1f)){onButtonClick("/")}
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Calbtn("7", Modifier.weight(1f)){onButtonClick("7")}
                    Calbtn("8", Modifier.weight(1f)){onButtonClick("8")}
                    Calbtn("9", Modifier.weight(1f)){onButtonClick("9")}
                    Calbtn("x", Modifier.weight(1f)){onButtonClick("x")}
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Calbtn("4", Modifier.weight(1f)){onButtonClick("4")}
                    Calbtn("5", Modifier.weight(1f)){onButtonClick("5")}
                    Calbtn("6", Modifier.weight(1f)){onButtonClick("6")}
                    Calbtn("-", Modifier.weight(1f)){onButtonClick("-")}
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Calbtn("1", Modifier.weight(1f)){onButtonClick("1")}
                    Calbtn("2", Modifier.weight(1f)){onButtonClick("2")}
                    Calbtn("3", Modifier.weight(1f)){onButtonClick("3")}
                    Calbtn("+", Modifier.weight(1f)){onButtonClick("+")}
                }
                Row(horizontalArrangement = Arrangement.spacedBy(28.dp)) {
                    Calbtn("0", Modifier.weight(2f)){onButtonClick("0")}
                    Calbtn(".", Modifier.weight(1f)){onButtonClick(".")}
                    Calbtn("=", Modifier.weight(1f)){onButtonClick("=")}
                }
            }
        }
    }
}
@Composable
fun Calbtn(text: String,modifier: Modifier= Modifier, onClick: () -> Unit){
    Box(
        contentAlignment = Alignment.Center,
        modifier= modifier
            .height(70.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(ColorButton)
            .clickable{onClick()}
    ){
        Text(text=text, fontSize = 30.sp,color=Color.White)
    }
}