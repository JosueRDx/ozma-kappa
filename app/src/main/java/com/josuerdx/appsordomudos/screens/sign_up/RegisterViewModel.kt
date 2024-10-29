package com.josuerdx.appsordomudos.screens.sign_up

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.common.snackbar.SnackbarManager
import com.josuerdx.appsordomudos.model.service.impl.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService,
    @ApplicationContext private val context: Context // Inyectamos el contexto
) : ViewModel() {

    var uiState = mutableStateOf(RegisterUiState())
        private set

    // Lógica de registro que utiliza los mensajes desde strings.xml
    fun onRegisterClick(onSuccess: () -> Unit) {
        // Obtener los mensajes de error desde strings.xml usando el contexto
        val passwordMismatchError = context.getString(R.string.password_match_error)
        val emailError = context.getString(R.string.email_error)
        val emailAlreadyInUseError = context.getString(R.string.email_already_in_use_error) // Nuevo mensaje para email ya registrado
        val emptyPasswordError = context.getString(R.string.empty_password_error) // Mensaje de contraseña vacía
        val passwordError = context.getString(R.string.password_error)
        val verificationEmailSent = context.getString(R.string.verification_email_sent)
        val genericError = context.getString(R.string.generic_error)

        // Validar los datos de registro
        if (!uiState.value.email.contains("@")) {
            viewModelScope.launch {
                SnackbarManager.showMessage(emailError)
            }
            return
        }

        // Verificar si la contraseña está vacía
        if (uiState.value.password.isEmpty()) {
            viewModelScope.launch {
                SnackbarManager.showMessage(emptyPasswordError) // Mensaje de contraseña vacía
            }
            return
        }

        // Verificar si la contraseña cumple con los requisitos de seguridad
        if (uiState.value.password.length < 6 || !uiState.value.password.any { it.isDigit() } ||
            !uiState.value.password.any { it.isLowerCase() } || !uiState.value.password.any { it.isUpperCase() }
        ) {
            viewModelScope.launch {
                SnackbarManager.showMessage(passwordError) // Mensaje de error de la contraseña
            }
            return
        }

        // Verificar si las contraseñas coinciden
        if (uiState.value.password != uiState.value.confirmPassword) {
            viewModelScope.launch {
                SnackbarManager.showMessage(passwordMismatchError) // Mensaje de que las contraseñas no coinciden
            }
            return
        }

        viewModelScope.launch {
            try {
                // Intentar registrar al usuario
                authService.register(uiState.value.email, uiState.value.password, uiState.value.confirmPassword)

                // Enviar un mensaje de éxito y esperar 3 segundos
                SnackbarManager.showMessage(verificationEmailSent)
                delay(2000) // Esperar 3 segundos
                onSuccess() // Redirigir a la pantalla de inicio de sesión

            } catch (e: Exception) {
                if (e.message == "email_error") {
                    SnackbarManager.showMessage(emailError) // Mensaje de correo inválido
                } else if (e.message == "email_already_in_use_error") {
                    SnackbarManager.showMessage(emailAlreadyInUseError) // Mensaje de correo ya en uso
                } else {
                    SnackbarManager.showMessage(e.message ?: genericError) // Mensaje genérico en caso de error
                }
            }
        }
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onConfirmPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(confirmPassword = newValue)
    }
}
