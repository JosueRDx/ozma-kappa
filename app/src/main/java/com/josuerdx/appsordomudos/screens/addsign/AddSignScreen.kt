// AddSignScreen.kt
package com.josuerdx.appsordomudos.screens.addsign

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.screens.MainLayout
import kotlinx.coroutines.delay

@Composable
fun AddSignScreen(
    viewModel: AddSignViewModel = hiltViewModel(),
    onSaveClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val signName by viewModel.signName.collectAsState()
    val description by viewModel.description.collectAsState()

    // Animación de tamaño
    var imageSize by remember { mutableStateOf(0.dp) }
    val animatedSize by animateDpAsState(targetValue = imageSize)

    // Iniciar la animación después de un pequeño retraso
    LaunchedEffect(Unit) {
        delay(200)
        imageSize = 170.dp // Tamaño final de la imagen después de la animación
    }

    MainLayout(
        title = "Agregar Seña",
        onHomeClick = onHomeClick,
        onUserClick = onUserClick,
        onMenuClick = onMenuClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logotipo en un círculo más grande y animado
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(animatedSize)
                    .border(2.dp, Color.Black, CircleShape)
                    .padding(16.dp) // Ajuste de espacio interno para que el logo quede bien centrado
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Asegúrate de tener el recurso logo en drawable
                    contentDescription = "Logo",
                    modifier = Modifier.size(120.dp) // Ajuste del tamaño del logo
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Campo para el nombre de la seña
            OutlinedTextField(
                value = signName,
                onValueChange = { viewModel.onSignNameChange(it) },
                label = { Text("Nombre de la Seña") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo para la descripción de la seña
            OutlinedTextField(
                value = description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de guardar
            Button(
                onClick = {
                    viewModel.saveSign()
                    onSaveClick() // Navega después de guardar
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Guardar Seña")
            }
        }
    }
}
