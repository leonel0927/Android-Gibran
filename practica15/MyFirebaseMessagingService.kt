package com.example.practica15

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // Se ejecuta automáticamente si el Token del dispositivo cambia
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_TOKEN", "Nuevo token generado: $token")
    }

    // Se ejecuta cuando recibimos un mensaje mientras la app está abierta
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // 1. Si el mensaje trae un "Notification Message" tradicional
        remoteMessage.notification?.let {
            Log.d("FCM_MENSAJE", "Cuerpo de la notificación: ${it.body}")
            mostrarNotificacion(
                this,
                "canal_practica_15",
                it.title ?: "Alerta",
                it.body ?: ""
            )
        }

        // 2. Si el mensaje trae información oculta tipo JSON
        if (remoteMessage.data.isNotEmpty()) {
            val tituloEspecial = remoteMessage.data["titulo_oculto"] ?: "Mensaje Oculto"
            val mensajeEspecial = remoteMessage.data["mensaje_oculto"] ?: "Tienes datos nuevos"
            mostrarNotificacion(this, "canal_practica_15", tituloEspecial, mensajeEspecial)
        }
    }
}
