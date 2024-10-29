package com.josuerdx.appsordomudos.screens.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.common.snackbar.SnackbarManager
import com.josuerdx.appsordomudos.model.service.impl.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    @ApplicationContext private val context: Context // Inyectamos el contexto aquí
) : ViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        // Obtener los mensajes desde strings.xml usando el contexto
        val emptyEmailError = context.getString(R.string.email_error)
        val emptyPasswordError = context.getString(R.string.empty_password_error)
        val emailNotVerifiedError = context.getString(R.string.email_not_verified_error)
        val invalidCredentialsError = context.getString(R.string.invalid_credentials_error)
        val genericError = context.getString(R.string.generic_error)
        val loginDetailsError = context.getString(R.string.login_details)

        // Verificar si el correo electrónico y la contraseña están vacíos
        if (uiState.value.email.isBlank() && uiState.value.password.isBlank()) {
            viewModelScope.launch {
                SnackbarManager.showMessage(loginDetailsError)
            }
            return
        }

        // Verificar si el correo está vacío
        if (uiState.value.email.isBlank()) {
            viewModelScope.launch {
                SnackbarManager.showMessage(emptyEmailError)
            }
            return
        }

        // Verificar si la contraseña está vacía
        if (uiState.value.password.isBlank()) {
            viewModelScope.launch {
                SnackbarManager.showMessage(emptyPasswordError)
            }
            return
        }

        viewModelScope.launch {
            try {
                // Intentar el inicio de sesión con Firebase
                authService.login(uiState.value.email, uiState.value.password)

                // Si el correo no está verificado
                if (!authService.isEmailVerified()) {
                    SnackbarManager.showMessage(emailNotVerifiedError)
                    return@launch
                }

                onSuccess()  // Navegar a la pantalla de inicio si el login es exitoso
            } catch (e: Exception) {
                // Si las credenciales son incorrectas
                if (e.message?.contains("password is invalid", ignoreCase = true) == true || e.message?.contains("There is no user record", ignoreCase = true) == true) {
                    SnackbarManager.showMessage(invalidCredentialsError)
                } else {
                    SnackbarManager.showMessage(e.message ?: genericError)
                }
            }
        }
    }
}
