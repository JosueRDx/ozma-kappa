package com.josuerdx.appsordomudos.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.josuerdx.appsordomudos.ui.theme.components.CustomTabBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    onCloseClick: () -> Unit = {}, // Acción para cerrar el perfil
    onSaveClick: () -> Unit = {}, // Acción para guardar los cambios
    onCancelClick: () -> Unit = {}, // Acción para cancelar
    onHomeClick: () -> Unit = {}, // Acción para el botón de Home
    onUserClick: () -> Unit = {}, // Acción para el botón de Perfil
    onMenuClick: () -> Unit = {}  // Acción para el botón de Menú
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF444444)), // Fondo oscuro
        contentAlignment = Alignment.Center
    ) {
        // Contenedor principal del perfil
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
                    IconButton(
                        onClick = onCloseClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Campos de información del usuario
                ProfileField("Nombres")
                ProfileField("Apellidos")
                ProfileField("Dirección")
                ProfileField("Correo")
                ProfileField("Más información")

                Spacer(modifier = Modifier.height(16.dp))

                // Botones guardar y cancelar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onSaveClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF512926)),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(text = "Guardar", color = Color.White)
                    }
                    OutlinedButton(
                        onClick = onCancelClick,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Cancelar")
                    }
                }
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

@Composable
fun ProfileField(fieldLabel: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = fieldLabel,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
