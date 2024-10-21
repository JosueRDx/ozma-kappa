package com.josuerdx.appsordomudos.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.R
import com.josuerdx.data.database.AppDatabase
import com.josuerdx.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mindrot.jbcrypt.BCrypt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    defaultEmail: String? = null,  // Nuevo argumento para pre-llenar el correo
    onLoginSuccess: (User) -> Unit = {},  // Pasamos el objeto `User` al iniciar sesión
    onLoginError: () -> Unit = {},   // Acción al fallar en la verificación
    onRegisterClick: () -> Unit = {} // Navegar a la pantalla de registro
) {
    var username by remember { mutableStateOf(defaultEmail ?: "") } // Usar el correo prellenado si existe
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val db = remember { AppDatabase.obtenerBaseDeDatos(context) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF444444) // Fondo oscuro
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo en círculo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp) // Tamaño
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Contenedor del formulario
            Surface(
                color = Color(0xFF6D3E39), // Color del contenedor
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth(0.89f)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Campo de texto para el nombre de usuario
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = {
                            Text(
                                text = "Nombre de Usuario",
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Campo de texto para la contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(
                                text = "Contraseña",
                                color = Color.White
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Mensaje de error
                    if (showError) {
                        Text(
                            text = "Credenciales incorrectas.",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Login
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val usuario = db.userDao().obtenerUsuarioPorCorreo(username)  // Buscar por correo en lugar de nombre
                        if (usuario != null && verificarContraseña(password, usuario.contraseña)) {
                            withContext(Dispatchers.Main) {
                                onLoginSuccess(usuario)  // Pasa el objeto `User` al callback de éxito
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                showError = true
                                onLoginError()
                            }
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3E39))
            ) {
                Text(text = "Iniciar Sesión", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto de Register
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("¿Nuevo Usuario?", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onRegisterClick) {
                    Text("Registrate", color = Color(0xFFD1A3A4), fontSize = 14.sp)
                }
            }
        }
    }
}

// Funciones de encriptación y verificación con BCrypt
fun encriptarContraseña(contraseña: String): String {
    return BCrypt.hashpw(contraseña, BCrypt.gensalt())
}

fun verificarContraseña(contraseña: String, encriptada: String): Boolean {
    return BCrypt.checkpw(contraseña, encriptada)
}
