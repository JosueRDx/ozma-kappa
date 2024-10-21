package com.josuerdx.appsordomudos.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.josuerdx.appsordomudos.UserData
import com.josuerdx.appsordomudos.ui.theme.components.CustomTabBar // Asegúrate de importar correctamente el TabBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    user: UserData,
    onCloseClick: () -> Unit,
    onHomeClick: () -> Unit,
    onUserClick: () -> Unit,  // Añadir el callback para el usuario
    onMenuClick: () -> Unit    // Añadir el callback para el menú
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF444444)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween // Espacio entre el contenido y el TabBar
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .padding(16.dp),
                color = Color(0xFF6D3E39),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Encabezado y botón de cerrar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Perfil Usuario",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        IconButton(onClick = onCloseClick) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Cerrar",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Información del usuario
                    Text(text = "Nombre: ${user.nombre}", color = Color.White)
                    Text(text = "Apellidos: ${user.apellido}", color = Color.White)
                    Text(text = "Correo: ${user.correo}", color = Color.White)
                    Text(text = "Dirección: ${user.direccion}", color = Color.White)

                    // Botones para guardar o cancelar
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { /* Manejar acción de guardar */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3E39))
                        ) {
                            Text(text = "Guardar", color = Color.White)
                        }
                        Button(
                            onClick = onHomeClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D3E39))
                        ) {
                            Text(text = "Cancelar", color = Color.White)
                        }
                    }
                }
            }

            // Implementar el TabBar en la parte inferior
            CustomTabBar(
                onHomeClick = onHomeClick,
                onUserClick = onUserClick,
                onMenuClick = onMenuClick
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        user = UserData(
            nombre = "Juan",
            apellido = "Pérez",
            correo = "juan.perez@example.com",
            direccion = "Calle Falsa 123"
        ),
        onCloseClick = { /* Acción de cerrar */ },
        onHomeClick = { /* Acción de volver a inicio */ },
        onUserClick = { /* Acción de perfil */ },
        onMenuClick = { /* Acción de menú */ }
    )
}
