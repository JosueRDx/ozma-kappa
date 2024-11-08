package com.josuerdx.appsordomudos.screens.addsign

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddSignViewModel : ViewModel() {

    // Estado para el nombre de la seña
    private val _signName = MutableStateFlow("")
    val signName: StateFlow<String> = _signName

    // Estado para la descripción de la seña
    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    // Función para actualizar el nombre de la seña
    fun onSignNameChange(newName: String) {
        _signName.update { newName }
    }

    // Función para actualizar la descripción de la seña
    fun onDescriptionChange(newDescription: String) {
        _description.update { newDescription }
    }

    // Función para guardar la seña
    fun saveSign() {
        // Aquí puedes implementar la lógica para guardar la seña en una base de datos o servicio.
        // Por ejemplo, guardarla en Firebase o en una base de datos local.
        println("Guardando seña: Nombre = ${_signName.value}, Descripción = ${_description.value}")
    }
}