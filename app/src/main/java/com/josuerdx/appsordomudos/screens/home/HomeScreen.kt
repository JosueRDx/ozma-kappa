// HomeScreen.kt
package com.josuerdx.appsordomudos.screens.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.screens.MainLayout
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val isConnected by viewModel.isConnected.collectAsState()
    val connectionMessage by viewModel.connectionMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val buttonColor by animateColorAsState(if (isConnected) Color.Red else Color.Green)
    val buttonText = if (isConnected) "Desconectar" else "Conectar"

    // Animación de tamaño para el logotipo
    var imageSize by remember { mutableStateOf(0.dp) }
    val animatedSize by animateDpAsState(targetValue = imageSize)

    // Retraso en la animación para dar un efecto de entrada
    LaunchedEffect(Unit) {
        delay(200)
        imageSize = 170.dp
    }

    MainLayout(
        title = "GestiGlove",
        onSettingsClick = onSettingsClick,
        onHomeClick = onHomeClick,
        onUserClick = onUserClick,
        onMenuClick = onMenuClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo dentro de un círculo con borde animado
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(animatedSize)
                    .border(2.dp, Color.Black, CircleShape)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Usa el recurso de tu logo
                    contentDescription = "Logo",
                    modifier = Modifier.size(120.dp) // Tamaño de la imagen en el centro del círculo
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de conexión
            Text(
                text = connectionMessage,
                fontSize = 18.sp,
                color = if (isConnected) Color.Green else Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón de conectar/desconectar con animación de color y parpadeo
            Button(
                onClick = { viewModel.onConnectionButtonClick() },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                enabled = !isLoading,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(50.dp)
                    .width(200.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(text = buttonText, color = Color.White, fontSize = 16.sp) // Usando buttonText directamente
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
