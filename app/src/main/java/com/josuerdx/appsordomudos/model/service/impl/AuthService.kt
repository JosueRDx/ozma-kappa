package com.josuerdx.appsordomudos.model.service.impl

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthService @Inject constructor(  // Anotamos el constructor con @Inject
    private val auth: FirebaseAuth
) {

    suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
        if (!auth.currentUser?.isEmailVerified!!) {
            throw Exception("Correo electrónico no verificado.")
        }
    }

    suspend fun register(email: String, password: String, confirmPassword: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
        auth.currentUser?.sendEmailVerification()?.await()
    }

    suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }
}
