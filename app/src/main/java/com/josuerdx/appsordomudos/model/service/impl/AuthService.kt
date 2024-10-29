package com.josuerdx.appsordomudos.model.service.impl

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthService @Inject constructor(
    private val auth: FirebaseAuth
) {

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    // Verificar si el correo electrónico ha sido verificado
    fun isEmailVerified(): Boolean {
        return auth.currentUser?.isEmailVerified == true
    }

    suspend fun register(email: String, password: String, confirmPassword: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser?.sendEmailVerification()?.await()
        } catch (e: Exception) {
            when {
                e.message?.contains("The email address is already in use") == true -> {
                    throw Exception("El correo electrónico ya está registrado.")
                }
                e.message?.contains("The email address is badly formatted") == true -> {
                    throw Exception("Por favor, introduce un correo electrónico válido.")
                }
                else -> throw e
            }
        }
    }
}
