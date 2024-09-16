package com.josuerdx.appsordomudos.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.josuerdx.appsordomudos.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    onCloseClick: () -> Unit = {} // Acción para cerrar el perfil
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
                .padding(16.dp),
            color = Color(0xFF6D3E39),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Título EchoHands centrado
                Text(
                    text = "EchoHands",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Campos de información del usuario
                ProfileField("Nombre")
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileField("Apellido")
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileField("Dirección")
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileField("Correo")
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileField("Más info")

                // Botón de cerrar
                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.user), // Asegúrate de tener un ícono adecuado
                        contentDescription = "Cerrar",
                        tint = Color.Red,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileField(fieldLabel: String) {
    Text(
        text = fieldLabel,
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
