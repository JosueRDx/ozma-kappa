package com.josuerdx.appsordomudos.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.josuerdx.appsordomudos.components.CustomTabBar
import com.josuerdx.appsordomudos.components.CustomTopBar

@Composable
fun MainLayout(
    title: String,
    onSettingsClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // TopBar en la parte superior
            CustomTopBar(
                title = title,
                onSettingsClick = onSettingsClick
            )

            // Contenido específico de la pantalla
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }

            // TabBar en la parte inferior
            CustomTabBar(
                onHomeClick = onHomeClick,
                onUserClick = onUserClick,
                onMenuClick = onMenuClick
            )
        }
    }
}