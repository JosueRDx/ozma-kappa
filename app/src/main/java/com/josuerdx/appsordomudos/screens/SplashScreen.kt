package com.josuerdx.appsordomudos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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
    // Fondo
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.inverseSurface,
    ) {
        // Centrar
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(210.dp)  // Tamaño
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