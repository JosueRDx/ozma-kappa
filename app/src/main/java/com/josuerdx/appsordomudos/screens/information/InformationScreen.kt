package com.josuerdx.appsordomudos.screens.information

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.screens.MainLayout

@Composable
fun InformationScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    MainLayout(
        title = "Información",
        onHomeClick = onHomeClick,
        onUserClick = onUserClick,
        onMenuClick = onMenuClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Imagen de encabezado
            Image(
                painter = painterResource(id = R.drawable.logo), // Asegúrate de tener un logo en drawable
                contentDescription = "Logo App",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .padding(bottom = 16.dp)
            )

            // Título de la aplicación
            Text(
                text = "GestiGlove",
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Información de versión
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Versión: 1.0.0",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // Información de desarrolladores
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Desarrolladores:",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "- Oozma Kappa\n- Equipo de Innovación",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            // Propósito de la aplicación
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Propósito:",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Con esta aplicación buscamos ayudar a personas con discapacidades auditivas, ofreciéndoles la posibilidad de editar para facilitar su comunicación y personalizar su experiencia.",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // Botón de regresar
            Button(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Regresar")
            }
        }
    }
}
