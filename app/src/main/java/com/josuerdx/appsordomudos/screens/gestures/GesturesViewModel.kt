package com.josuerdx.appsordomudos.screens.gestures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.appsordomudos.model.Gesto
import com.josuerdx.appsordomudos.model.service.api.GestoApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GesturesViewModel : ViewModel() {
    private val _gestos = MutableStateFlow<List<Gesto>>(emptyList())
    val gestos: StateFlow<List<Gesto>> = _gestos

    private val _editResult = MutableStateFlow<String?>(null)
    val editResult: StateFlow<String?> = _editResult

    init {
        fetchGestos()
    }

    private fun fetchGestos() {
        viewModelScope.launch {
            val response = GestoApiClient.service.getGestos()
            if (response.isSuccessful) {
                _gestos.value = response.body() ?: emptyList()
            } else {
                _editResult.value = "Error al cargar gestos"
            }
        }
    }

    fun updateGesto(gestoId: Int, nuevoSignificado: String) {
        viewModelScope.launch {
            val gesto = _gestos.value.find { it.id == gestoId }?.copy(significado = nuevoSignificado)
            if (gesto != null) {
                val response = GestoApiClient.service.updateGesto(gestoId, gesto)
                if (response.isSuccessful) {
                    _editResult.value = "Gesto actualizado correctamente"
                    fetchGestos() // Actualiza la lista después de editar
                } else {
                    _editResult.value = "Error al actualizar el gesto"
                }
            }
        }
    }
}