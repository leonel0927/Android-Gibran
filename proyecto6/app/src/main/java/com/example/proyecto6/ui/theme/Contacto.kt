package com.example.proyecto6.ui.theme

data class Contacto(
    val id: Int,
    var nombre: String,
    var numero : String
)

fun ObtenerContactos(): List<Contacto>{
return listOf(
    Contacto(1, "Leonel", "668925845"),
    Contacto(2, "María García", "6681234567"),
    Contacto(3, "Carlos Mendoza", "6688765432"),
    Contacto(4, "Ana Martínez", "6685551234"),
    Contacto(5, "Roberto Gómez", "6684449876"),
    Contacto(6, "Sofía López", "6683332211"),
    Contacto(7, "Javier Hernández", "6687778899")
)
}