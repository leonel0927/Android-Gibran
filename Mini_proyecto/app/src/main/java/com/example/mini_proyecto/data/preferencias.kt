package com.example.mini_proyecto.data

import android.content.*
class preferencias(context:Context){
    private val sharedPreferences: SharedPreferences= context.getSharedPreferences("UserPreferences",Context.MODE_PRIVATE)
    companion object{
        const val KEY_NOMBRE="key_nombre"
        const val KEY_APELLIDO1="key_apellido1"
        const val KEY_APELLIDO2="key_apellido2"
        const val KEY_MATRICULA="key_matricula"
        const val KEY_CARRERA="key_carrera"
        const val KEY_ACTIVO="key_activo"
        const val KEY_TURNO="key_turno"
    }
    fun saveData(Nombre: String,AP1: String,AP2: String,Matricula: String,Carrea: String,Turno: String,Activo: Boolean){
        val editor=sharedPreferences.edit()
        editor.putString(KEY_NOMBRE,Nombre)
        editor.putString(KEY_APELLIDO1,AP1)
        editor.putString(KEY_APELLIDO2,AP2)
        editor.putString(KEY_MATRICULA,Matricula)
        editor.putString(KEY_TURNO,Turno)
        editor.putString(KEY_CARRERA,Carrea)
        editor.putBoolean(KEY_ACTIVO,Activo)
        editor.apply()
    }
    fun getNombre(): String = sharedPreferences.getString(KEY_NOMBRE, "") ?: ""
    fun getApellido1(): String = sharedPreferences.getString(KEY_APELLIDO1, "") ?: ""
    fun getApellido2(): String = sharedPreferences.getString(KEY_APELLIDO2, "") ?: ""
    fun getMatricula(): String = sharedPreferences.getString(KEY_MATRICULA, "") ?: ""
    fun getCarrera(): String = sharedPreferences.getString(KEY_CARRERA, "") ?: ""
    fun getTurno(): String = sharedPreferences.getString(KEY_TURNO, "") ?: ""
    fun getActivo(): Boolean = sharedPreferences.getBoolean(KEY_ACTIVO, false)
}