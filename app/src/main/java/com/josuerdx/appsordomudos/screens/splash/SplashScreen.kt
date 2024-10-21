package com.josuerdx.appsordomudos.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josuerdx.appsordomudos.R

@Composable
fun SplashScreen() {
    // Fondo oscuro para mantener consistencia
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF1C1C1C), // Fondo siempre oscuro
    ) {
        // Centrar el logo
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),  // Asegúrate de que el logo esté en drawable-nodpi
                contentDescription = "Logo",
                modifier = Modifier
                    .size(210.dp)  // Tamaño del logo
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
