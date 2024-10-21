package com.josuerdx.data.mqtt

import android.content.Context
import android.util.Log
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence
import java.io.ByteArrayInputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

class MqttClientHelper(private val context: Context) {

    interface OnConnectionListener {
        fun onConnected()
        fun onConnectionFailed(error: String)
    }

    // Listener para los mensajes MQTT
    interface OnMessageArrivedListener {
        fun onMessageArrived(message: String)
    }

    private var connectionListener: OnConnectionListener? = null
    private var messageArrivedListener: OnMessageArrivedListener? = null

    fun setOnConnectionListener(listener: OnConnectionListener) {
        connectionListener = listener
    }

    // Método para registrar el listener de mensajes
    fun setOnMessageArrivedListener(listener: OnMessageArrivedListener) {
        messageArrivedListener = listener
    }

    private val serverUri = "ssl://p5d4eb0f.ala.eu-central-1.emqxsl.com:8883"
    private val clientId = "android_client" + System.currentTimeMillis()
    private val mqttUsername = "Rodrigo"
    private val mqttPassword = "RDX123"

    // Aquí cambiamos el tópico para que usemos el comodín +
    private val topic = "/sensores/parse"

    private val persistence: MqttClientPersistence = MqttDefaultFilePersistence(context.cacheDir.absolutePath)
    private val mqttClient = MqttAsyncClient(serverUri, clientId, persistence)

    init {
        val options = MqttConnectOptions().apply {
            userName = mqttUsername
            password = mqttPassword.toCharArray()
            isAutomaticReconnect = true
            isCleanSession = true
            socketFactory = getSocketFactory()
        }

        mqttClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                connectionListener?.onConnectionFailed(cause?.message ?: "Conexión perdida")
                Log.d("MQTT", "Conexión perdida: ${cause?.message}")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                val payload = message?.payload?.toString(Charsets.UTF_8)
                Log.d("MQTT", "Mensaje recibido de $topic: $payload")

                // Notificamos al listener que un mensaje ha llegado
                messageArrivedListener?.onMessageArrived(payload ?: "Sin mensaje")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d("MQTT", "Mensaje entregado")
            }
        })

        try {
            mqttClient.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    connectionListener?.onConnected()
                    Log.d("MQTT", "Conexión exitosa a MQTT")

                    // Suscribimos al tópico con comodín +
                    subscribeToTopic(topic)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    val errorMessage = exception?.message ?: "Error desconocido"
                    connectionListener?.onConnectionFailed(errorMessage)
                    Log.d("MQTT", "Error al conectar a MQTT: $errorMessage")
                    exception?.printStackTrace()
                }
            })
        } catch (e: MqttException) {
            connectionListener?.onConnectionFailed(e.message ?: "Error de conexión al inicializar")
            Log.d("MQTT", "Excepción al conectar: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun subscribeToTopic(topic: String) {
        try {
            mqttClient.subscribe(topic, 1, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("MQTT", "Suscripción exitosa al tópico: $topic")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.e("MQTT", "Error al suscribirse al tópico: ${exception?.message}")
                }
            })
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun publishMessage(message: String) {
        try {
            val mqttMessage = MqttMessage().apply {
                payload = message.toByteArray()
            }
            mqttClient.publish(topic, mqttMessage)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    fun disconnect() {
        mqttClient.disconnect()
    }

    private fun getSocketFactory(): SSLSocketFactory? {
        return try {
            val cf = CertificateFactory.getInstance("X.509")
            val caCert: X509Certificate = cf.generateCertificate(ByteArrayInputStream(ROOT_CA.toByteArray())) as X509Certificate

            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType()).apply {
                load(null, null)
                setCertificateEntry("caCert", caCert)
            }

            val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
                init(keyStore)
            }

            SSLContext.getInstance("TLS").apply {
                init(null, tmf.trustManagers, null)
            }.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        val ROOT_CA = """
            -----BEGIN CERTIFICATE-----
            MIIDrzCCApegAwIBAgIQCDvgVpBCRrGhdWrJWZHHSjANBgkqhkiG9w0BAQUFADBh
            ...
            -----END CERTIFICATE-----
        """.trimIndent()
    }
}
