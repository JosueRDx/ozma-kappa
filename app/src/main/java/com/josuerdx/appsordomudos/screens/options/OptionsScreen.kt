// OptionsScreen.kt
package com.josuerdx.appsordomudos.screens.options

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Opción: Agregar Seña
            OptionCard(
                title = "Agregar Seña",
                icon = Icons.Default.Add,
                color = Color(0xFF4CAF50),
                onClick = onAddSignClick
            )

            // Opción: Traductor
            OptionCard(
                title = "Traductor",
                icon = Icons.Default.Language, // Alternativa para Translate
                color = Color(0xFF2196F3),
                onClick = onTranslatorClick
            )

            // Opción: Mis Gestos
            OptionCard(
                title = "Mis Gestos",
                icon = Icons.Default.PanTool, // Alternativa para Gesture
                color = Color(0xFFFF9800),
                onClick = onMyGesturesClick
            )

            // Opción: Información
            OptionCard(
                title = "Información",
                icon = Icons.Default.Info,
                color = Color(0xFFF44336),
                onClick = onInfoClick
            )
        }
    }
}

@Composable
fun OptionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .height(70.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .clickable { onClick() }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "$title Icon",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = title,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}
