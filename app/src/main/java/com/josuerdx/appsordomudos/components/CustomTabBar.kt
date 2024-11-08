// CustomTabBar.kt
package com.josuerdx.appsordomudos.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CustomTabBar(
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {} // Asegúrate de que el parámetro esté aquí
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.scrim,
        shadowElevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            // User con efecto de clic
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "User Profile",
                tint = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .size(45.dp)
                    .clickable(
                        onClick = onUserClick,
                        indication = rememberRipple(bounded = false, color = Color.White),
                        interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource()
                    )
            )

            // Home con efecto de clic
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home",
                tint = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .size(45.dp)
                    .clickable(
                        onClick = onHomeClick,
                        indication = rememberRipple(bounded = false, color = Color.White),
                        interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource()
                    )
            )

            // Menu con efecto de clic
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .size(45.dp)
                    .clickable(
                        onClick = onMenuClick, // Llama a onMenuClick para abrir la pantalla de opciones
                        indication = rememberRipple(bounded = false, color = Color.White),
                        interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource()
                    )
            )
        }
    }
}
