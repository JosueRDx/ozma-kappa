// OptionsViewModel.kt
package com.josuerdx.appsordomudos.screens.options

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OptionsViewModel : ViewModel() {

    // Estado para el título de la pantalla de opciones
    private val _title = MutableStateFlow("Opciones")
    val title: StateFlow<String> = _title

    // Podrías añadir aquí más estado o lógica según las necesidades de la pantalla de opciones.
}
