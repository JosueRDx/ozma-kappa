// GesturesScreen.kt
package com.josuerdx.appsordomudos.screens.gestures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josuerdx.appsordomudos.screens.MainLayout

@Composable
fun GesturesScreen(
    viewModel: GesturesViewModel = hiltViewModel(),
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val gestos by viewModel.gestos.collectAsState()
    val editResult by viewModel.editResult.collectAsState()

    MainLayout(
        title = "Mis Gestos",
        onHomeClick = onHomeClick,
        onUserClick = onUserClick,
        onMenuClick = onMenuClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Mis Gestos", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

            if (editResult != null) {
                Text(text = editResult!!, color = androidx.compose.ui.graphics.Color.Red)
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(gestos.size) { index ->
                    val gesto = gestos[index]
                    var newSignificado by remember { mutableStateOf(gesto.significado) }

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "ID: ${gesto.id}", fontSize = 16.sp)
                        OutlinedTextField(
                            value = newSignificado,
                            onValueChange = { newSignificado = it },
                            label = { Text("Significado") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Button(
                            onClick = { viewModel.updateGesto(gesto.id, newSignificado) },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(text = "Actualizar Significado")
                        }
                    }
                }
            }
        }
    }
}
