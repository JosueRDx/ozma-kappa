package com.josuerdx.appsordomudos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.josuerdx.appsordomudos.ui.theme.AppSordoMudosTheme
import com.josuerdx.appsordomudos.ui.theme.screens.MessageScreen
import com.josuerdx.appsordomudos.ui.theme.screens.MessageViewModel
import com.josuerdx.data.mqtt.MqttClientHelper


class MainActivity : ComponentActivity() {

    // Inicializamos el ViewModel para los mensajes MQTT
    private val messageViewModel: MessageViewModel by viewModels()
    private lateinit var mqttClientHelper: MqttClientHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el cliente MQTT
        mqttClientHelper = MqttClientHelper(this).apply {
            setOnConnectionListener(object : MqttClientHelper.OnConnectionListener {
                override fun onConnected() {
                    Log.d("MainActivity", "Conexión exitosa a MQTT")
                }

                override fun onConnectionFailed(error: String) {
                    Log.e("MainActivity", "Error en la conexión MQTT: $error")
                }
            })
        }

        // Asigna el listener para manejar los mensajes recibidos desde el tópico MQTT
        mqttClientHelper.setOnMessageArrivedListener(object : MqttClientHelper.OnMessageArrivedListener {
            override fun onMessageArrived(message: String) {
                // Actualiza el mensaje en el ViewModel
                messageViewModel.setMessage(message)
            }
        })

        // Configura la UI de Compose
        setContent {
            AppSordoMudosTheme {
                // Pasamos el ViewModel a la pantalla para que Compose pueda observar los mensajes
                MessageScreen(viewModel = messageViewModel)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Desconectar del cliente MQTT al destruir la actividad
        mqttClientHelper.disconnect()
    }
}
