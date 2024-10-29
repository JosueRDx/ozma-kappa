package com.josuerdx.appsordomudos.screens.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.common.composable.TextFieldComposable
import com.josuerdx.appsordomudos.common.snackbar.SnackbarManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val uiState by viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Escuchar los mensajes del SnackbarManager y mostrarlos en el Snackbar
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            SnackbarManager.messages.collectLatest { message ->
                snackbarHostState.showSnackbar(message.message)
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF444444) // Fondo oscuro
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Contenido del formulario
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
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Contenedor del formulario
                Surface(
                    color = Color(0xFF6D3E39),
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
                            text = stringResource(id = R.string.sign_in),
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Email
                        TextFieldComposable(
                            value = uiState.email,
                            onValueChange = viewModel::onEmailChange,
                            label = stringResource(id = R.string.email)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        // Password
                        TextFieldComposable(
                            value = uiState.password,
                            onValueChange = viewModel::onPasswordChange,
                            label = stringResource(id = R.string.password),
                            isPassword = true
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón de Login
                Button(
                    onClick = { viewModel.onLoginClick(onLoginSuccess) },
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3E39))
                ) {
                    Text(text = stringResource(id = R.string.sign_in), color = Color.White, fontSize = 16.sp)
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

            // SnackbarHost en la parte inferior
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = { data ->
                        Snackbar(
                            snackbarData = data,
                            containerColor = Color.DarkGray,
                            contentColor = Color.White
                        )
                    }
                )
            }
        }
    }
}
