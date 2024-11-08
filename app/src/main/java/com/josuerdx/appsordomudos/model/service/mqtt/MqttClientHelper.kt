package com.josuerdx.appsordomudos.model.service.mqtt

import android.content.Context
import android.util.Log
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence

class MqttClientHelper(
    context: Context,
    private val serverUri: String = "ssl://p5d4eb0f.ala.eu-central-1.emqxsl.com:8883",
    private val clientId: String = "android_client" + System.currentTimeMillis(),
    private val username: String = "Rodrigo",
    private val password: String = "RDX123",
    private val topic: String = "/sensores/parse"
) {

    private var mqttClient: MqttAsyncClient? = null
    private val options: MqttConnectOptions

    init {
        val persistence = MqttDefaultFilePersistence(context.cacheDir.absolutePath)
        mqttClient = MqttAsyncClient(serverUri, clientId, persistence)

        options = MqttConnectOptions().apply {
            userName = username
            password = this@MqttClientHelper.password.toCharArray()
            isAutomaticReconnect = true
            isCleanSession = true
        }

        mqttClient?.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                Log.d("MQTT", "Conexión perdida: ${cause?.message}")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                val payload = message?.payload?.toString(Charsets.UTF_8)
                Log.d("MQTT", "Mensaje recibido de $topic: $payload")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d("MQTT", "Mensaje entregado")
            }
        })
    }

    fun connect(onSuccess: () -> Unit = {}, onFailure: (String) -> Unit = {}) {
        try {
            mqttClient?.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("MQTT", "Conexión exitosa")
                    subscribeToTopic()
                    onSuccess()
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.e("MQTT", "Error al conectar: ${exception?.message}")
                    onFailure(exception?.message ?: "Error desconocido")
                }
            })
        } catch (e: MqttException) {
            onFailure(e.message ?: "Error desconocido")
            e.printStackTrace()
        }
    }

    private fun subscribeToTopic() {
        try {
            mqttClient?.subscribe(topic, 1)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun publishMessage(message: String) {
        try {
            val mqttMessage = MqttMessage().apply {
                payload = message.toByteArray()
            }
            mqttClient?.publish(topic, mqttMessage)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        mqttClient?.disconnect()
    }
}