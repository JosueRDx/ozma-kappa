package com.josuerdx.appsordomudos.screens.sign_up

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.appsordomudos.model.service.impl.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService  // Servicio para manejar la autenticación
) : ViewModel() {

    // Estado de la UI para manejar los valores del formulario
    var uiState = mutableStateOf(RegisterUiState())
        private set

    // Función para actualizar el correo electrónico
    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    // Función para actualizar la contraseña
    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    // Función para actualizar la confirmación de la contraseña
    fun onConfirmPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(confirmPassword = newValue)
    }

    // Lógica de registro
    fun onRegisterClick(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (uiState.value.password != uiState.value.confirmPassword) {
            onError("Las contraseñas no coinciden.")
            return
        }

        viewModelScope.launch {
            try {
                authService.register(uiState.value.email, uiState.value.password, uiState.value.confirmPassword)
                onSuccess()  // Registro exitoso
            } catch (e: Exception) {
                onError("Error de registro: ${e.message}")
            }
        }
    }
}
