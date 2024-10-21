package com.josuerdx.appsordomudos.ui.theme.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel : ViewModel() {
    private val _message = MutableLiveData<String>("Esperando mensajes...") // Valor inicial

    val message: LiveData<String> get() = _message

    fun setMessage(newMessage: String) {
        _message.value = newMessage
    }
}