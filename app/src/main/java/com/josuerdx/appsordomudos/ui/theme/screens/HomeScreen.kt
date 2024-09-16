package com.josuerdx.appsordomudos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.ui.theme.components.CustomTabBar

@Composable
fun HomeScreen(
    onConnectionClick: () -> Unit = {},
    onLearningClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onTranslateClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White // Fondo
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Título centrado
            Text(
                text = "EchoHands",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
            )

            // contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Espaciador
                Spacer(modifier = Modifier.height(80.dp))

                // Column centrada
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(90.dp)
                ) {
                    // Conexión y Aprendizaje
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(100.dp)
                    ) {
                        HomeIconItem(
                            iconRes = R.drawable.hhome,
                            onClick = onConnectionClick
                        )
                        HomeIconItem(
                            iconRes = R.drawable.hhome,
                            onClick = onLearningClick
                        )
                    }

                    // Configuración y Agregar
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(150.dp)
                    ) {
                        HomeIconItem(
                            iconRes = R.drawable.hhome,
                            onClick = onSettingsClick
                        )
                        HomeIconItem(
                            iconRes = R.drawable.hhome,
                            onClick = onAddClick
                        )
                    }

                    // Traducir
                    HomeIconItem(
                        iconRes = R.drawable.hhome,
                        onClick = onTranslateClick
                    )
                }
            }

            // TabBar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                CustomTabBar(
                    onHomeClick = onHomeClick,
                    onUserClick = onUserClick,
                    onMenuClick = onMenuClick
                )
            }
        }
    }
}

@Composable
fun HomeIconItem(iconRes: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(80.dp) // Tamaño de los íconos
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
