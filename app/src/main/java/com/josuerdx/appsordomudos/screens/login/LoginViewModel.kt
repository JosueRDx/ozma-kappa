package com.josuerdx.appsordomudos.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.appsordomudos.model.service.impl.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService  // Asegúrate de inyectar el servicio correcto
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onLoginClick(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (uiState.value.email.isBlank() || uiState.value.password.isBlank()) {
            onError("Los campos no pueden estar vacíos")
            return
        }

        viewModelScope.launch {
            try {
                authService.login(uiState.value.email, uiState.value.password)
                onSuccess()  // Navegar a la pantalla de inicio si el login es exitoso
            } catch (e: Exception) {
                onError("Error de autenticación: ${e.message}")
            }
        }
    }
}
