    package com.example.calculadora.ui.theme

    object LogicaCal {
        fun Calcular(numero1: String, numero2: String, Operador: String): String {
            val n1 = numero1.toDoubleOrNull() ?: return ""
            val n2 = numero2.toDoubleOrNull() ?: return ""
            val resultado = when (Operador) {
                "+" -> n1 + n2
                "-" -> n1 - n2
                "x" -> n1 * n2
                "/" -> if (n2 != 0.0) n1 / n2 else Double.NaN
                else -> 0.0
            }
            return  if (resultado % 1.0 == 0.0){
                resultado.toLong().toString()
            }else{
                resultado.toString()
            }
        }
        fun Porcentaje(n1: String): String{
            val n= n1.toDoubleOrNull() ?: return ""
            val resultado = n/100
            return if (resultado % 1.0==0.0) resultado.toLong().toString() else resultado.toString()
        }
    }