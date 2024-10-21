package com.josuerdx.appsordomudos.screens.sign_up

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.common.composable.TextFieldComposable
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegisterSuccess: () -> Unit
) {
    val uiState by viewModel.uiState
    val coroutineScope = rememberCoroutineScope()
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF444444) // Fondo oscuro
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

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
                        text = stringResource(id = R.string.create_account),
                        color = Color.White,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Email
                    TextFieldComposable(
                        value = uiState.email,
                        onValueChange = viewModel::onEmailChange,
                        label = stringResource(id = R.string.email)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    // Password
                    TextFieldComposable(
                        value = uiState.password,
                        onValueChange = viewModel::onPasswordChange,
                        label = stringResource(id = R.string.password),
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    // Confirm Password
                    TextFieldComposable(
                        value = uiState.confirmPassword,
                        onValueChange = viewModel::onConfirmPasswordChange,
                        label = stringResource(id = R.string.repeat_password),
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    // Mensaje de error
                    if (showError) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón de Register
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.onRegisterClick(
                            onSuccess = {
                                showError = false
                                onRegisterSuccess()
                            },
                            onError = {
                                showError = true
                                errorMessage = it
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3E39))
            ) {
                Text(text = stringResource(id = R.string.create_account), color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
