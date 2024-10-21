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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.R
import com.josuerdx.data.database.AppDatabase
import com.josuerdx.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RegisterScreen(
    onRegisterClick: (User) -> Unit = {} // Acepta un objeto User en vez de dos strings
) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val db = remember { AppDatabase.obtenerBaseDeDatos(context) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1C1C1C) // Fondo
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Contenedor del formulario
            Surface(
                color = Color(0xFF6D3E39),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registrarse",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Campos de entrada
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombres", color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellidos", color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo", color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = { Text("Dirección", color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña", color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar contraseña", color = Color.White) },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD1A3A4),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color.White
                        )
                    )

                    // Mensaje de error
                    if (showError) {
                        Text(
                            text = "Completa todos los campos correctamente",
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Botón de Register
                    Button(
                        onClick = {
                            if (name.isNotEmpty() && lastName.isNotEmpty() && correo.isNotEmpty() && direccion.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword) {
                                // Encripta la contraseña
                                val encryptedPassword = encriptarContraseña(password)

                                // Crear objeto usuario
                                val usuario = User(
                                    nombre = name,
                                    apellido = lastName,
                                    correo = correo,
                                    direccion = direccion,
                                    contraseña = encryptedPassword
                                )

                                // Insertar usuario en la base de datos
                                CoroutineScope(Dispatchers.IO).launch {
                                    db.userDao().insertar(usuario)
                                    withContext(Dispatchers.Main) {
                                        // Pasar el objeto User
                                        onRegisterClick(usuario) // Pasar el objeto usuario a ProfileScreen
                                    }
                                }
                            } else {
                                showError = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3E39))
                    ) {
                        Text(text = "Registrarse", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}

