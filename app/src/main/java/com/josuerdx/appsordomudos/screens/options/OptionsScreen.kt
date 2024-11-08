// OptionsScreen.kt
package com.josuerdx.appsordomudos.screens.options

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josuerdx.appsordomudos.screens.MainLayout

@Composable
fun OptionsScreen(
    viewModel: OptionsViewModel = hiltViewModel(),
    onAddSignClick: () -> Unit = {},
    onTranslatorClick: () -> Unit = {},
    onMyGesturesClick: () -> Unit = {},
    onInfoClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},    // Navegación a Home
    onUserClick: () -> Unit = {},    // Navegación a Perfil
    onMenuClick: () -> Unit = {}     // Navegación a Opciones
) {
    val title = viewModel.title.collectAsState().value

    MainLayout(
        title = title,
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
            Text(text = title, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

            Button(
                onClick = onAddSignClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Agregar Seña")
            }

            Button(
                onClick = onTranslatorClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Traductor")
            }

            Button(
                onClick = onMyGesturesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Mis Gestos")
            }

            Button(
                onClick = onInfoClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Información")
            }
        }
    }
}
