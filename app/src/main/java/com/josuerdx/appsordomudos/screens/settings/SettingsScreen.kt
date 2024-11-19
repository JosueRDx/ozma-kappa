package com.josuerdx.appsordomudos.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.screens.MainLayout

@Composable
fun SettingsScreen(
    onLogoutClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    var isNotificationsEnabled by remember { mutableStateOf(false) }
    var isThemeDialogOpen by remember { mutableStateOf(false) }
    var isLanguageDialogOpen by remember { mutableStateOf(false) }

    MainLayout(
        title = "Configuraciones",
        onHomeClick = onHomeClick,
        onUserClick = onUserClick,
        onMenuClick = onMenuClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Configuraciones",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Opción de Notificaciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Notificaciones", fontSize = 18.sp)
                Switch(
                    checked = isNotificationsEnabled,
                    onCheckedChange = { isNotificationsEnabled = it }
                )
            }

            // Opción de Tema
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isThemeDialogOpen = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tema", fontSize = 18.sp)
                Icon(imageVector = Icons.Default.Palette, contentDescription = "Tema Icono")
            }

            // Opción de Idioma
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isLanguageDialogOpen = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Idioma", fontSize = 18.sp)
                Icon(imageVector = Icons.Default.Language, contentDescription = "Idioma Icono")
            }

            // Botón de Cerrar Sesión
            Button(
                onClick = onLogoutClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Cerrar Sesión")
            }
        }

        // Pantalla emergente para seleccionar Tema
        if (isThemeDialogOpen) {
            ThemeSelectionDialog(onDismiss = { isThemeDialogOpen = false })
        }

        // Pantalla emergente para seleccionar Idioma
        if (isLanguageDialogOpen) {
            LanguageSelectionDialog(onDismiss = { isLanguageDialogOpen = false })
        }
    }
}

@Composable
fun ThemeSelectionDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Seleccionar Tema") },
        text = {
            Column {
                Text(text = "Claro", modifier = Modifier.clickable { onDismiss() })
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Oscuro", modifier = Modifier.clickable { onDismiss() })
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text(text = "Aceptar") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(text = "Cancelar") } }
    )
}

@Composable
fun LanguageSelectionDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Seleccionar Idioma") },
        text = {
            Column {
                Text(text = "Español", modifier = Modifier.clickable { onDismiss() })
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Inglés", modifier = Modifier.clickable { onDismiss() })
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text(text = "Aceptar") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(text = "Cancelar") } }
    )
}
