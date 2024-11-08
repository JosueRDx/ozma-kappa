package com.josuerdx.appsordomudos.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.appsordomudos.model.service.mqtt.MqttClientHelper
import com.josuerdx.appsordomudos.model.service.mqtt.MqttModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val mqttClientHelper: MqttClientHelper = MqttModule.provideMqttClientHelper(application.applicationContext)

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    private val _connectionMessage = MutableStateFlow("Desconectado")
    val connectionMessage: StateFlow<String> = _connectionMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onConnectionButtonClick() {
        if (_isConnected.value) {
            disconnectFromMqtt()
        } else {
            connectToMqtt()
        }
    }

    private fun connectToMqtt() {
        _isLoading.value = true
        _connectionMessage.value = "Conectando..."

        mqttClientHelper.connect(
            onSuccess = {
                viewModelScope.launch {
                    delay(1000) // Simulamos un tiempo de conexión
                    _isConnected.value = true
                    _isLoading.value = false
                    _connectionMessage.value = "Conectado correctamente"
                }
            },
            onFailure = { error ->
                viewModelScope.launch {
                    _isConnected.value = false
                    _isLoading.value = false
                    _connectionMessage.value = "Error de conexión: $error"
                }
            }
        )
    }

    private fun disconnectFromMqtt() {
        _isLoading.value = true
        _connectionMessage.value = "Desconectando..."

        viewModelScope.launch {
            delay(1000) // Simulamos un tiempo de desconexión
            mqttClientHelper.disconnect()
            _isConnected.value = false
            _isLoading.value = false
            _connectionMessage.value = "Desconectado"
        }
    }
}
