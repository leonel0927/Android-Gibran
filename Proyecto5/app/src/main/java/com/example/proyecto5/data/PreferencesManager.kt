package com.example.proyecto5.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context:Context) {
    private val sharedPreferences: SharedPreferences= context.getSharedPreferences("UserPreferences",Context.MODE_PRIVATE)
    companion object{
        const val KEY_USERNAME="key_username"
        const val KEY_NOTIFICATION="key_notification"
        const val KEY_TEMA="key_tema"
    }
    fun saveSettings(User: String,notificacion: Boolean,tema: Boolean){
        val editor=sharedPreferences.edit()
        editor.putString(KEY_USERNAME,User)
        editor.putBoolean(KEY_NOTIFICATION,notificacion)
        editor.putBoolean(KEY_TEMA,tema)
        editor.apply()
    }
    fun getUser(): String{
        return sharedPreferences.getString(KEY_USERNAME,"") ?: ""
    }
    fun getNotification(): Boolean{
        return sharedPreferences.getBoolean(KEY_NOTIFICATION, false)
    }
    fun getTema(): Boolean{
        return  sharedPreferences.getBoolean(KEY_TEMA,false )
    }
    fun clear(){
        sharedPreferences.edit().clear().apply()
    }
}