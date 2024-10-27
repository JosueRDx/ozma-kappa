package com.josuerdx.appsordomudos.common.snackbar

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableSharedFlow

object SnackbarManager {
    val messages = MutableSharedFlow<SnackbarMessage>()

    suspend fun showMessage(message: String) {
        messages.emit(SnackbarMessage(message))
    }
}
