package com.josuerdx.appsordomudos.screens.gestures

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.josuerdx.appsordomudos.screens.MainLayout

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GesturesScreen(
    viewModel: GesturesViewModel = hiltViewModel(),
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val gestos by viewModel.gestos.collectAsState()
    val editResult by viewModel.editResult.collectAsState()
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedGesto by remember { mutableStateOf<Pair<Int, String>?>(null) }

    // Mostrar el mensaje con un temporizador
    LaunchedEffect(editResult) {
        if (editResult != null) {
            kotlinx.coroutines.delay(2000) // Espera 2 segundos
            viewModel.clearEditResult() // Limpia el mensaje en el ViewModel después del tiempo
        }
    }

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

            // Mensaje de éxito con animación llamativa
            AnimatedVisibility(
                visible = editResult != null,
                enter = slideInHorizontally(initialOffsetX = { -300 }) +
                        expandVertically(expandFrom = Alignment.Top) +
                        fadeIn(initialAlpha = 0.3f),
                exit = slideOutVertically(targetOffsetY = { 300 }) +
                        shrinkVertically(shrinkTowards = Alignment.Bottom) +
                        fadeOut(targetAlpha = 0.1f)
            ) {
                if (editResult != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(50.dp)
                            .background(Color(0xFFA5D6A7), shape = MaterialTheme.shapes.medium), // Fondo verde
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = editResult!!,
                            fontSize = 16.sp,
                            color = Color.White, // Texto blanco
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(gestos.size) { index ->
                    val gesto = gestos[index]

                    GestureItem(
                        significado = gesto.significado,
                        onEditClick = {
                            selectedGesto = gesto.id to gesto.significado
                            isDialogOpen = true
                        }
                    )
                }
            }

            // Dialog para editar gesto
            if (isDialogOpen && selectedGesto != null) {
                EditGestoDialog(
                    currentSignificado = selectedGesto!!.second,
                    onSave = { newSignificado ->
                        viewModel.updateGesto(selectedGesto!!.first, newSignificado)
                        isDialogOpen = false
                    },
                    onCancel = { isDialogOpen = false }
                )
            }
        }
    }
}

@Composable
fun GestureItem(
    significado: String,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = significado,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar",
                    tint = Color.Gray
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditGestoDialog(
    currentSignificado: String,
    onSave: (String) -> Unit,
    onCancel: () -> Unit
) {
    var newSignificado by remember { mutableStateOf(currentSignificado) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text(
                text = "Editar Significado",
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        text = {
            OutlinedTextField(
                value = newSignificado,
                onValueChange = { newSignificado = it },
                label = { Text("Nuevo Significado") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        },
        confirmButton = {
            TextButton(onClick = { onSave(newSignificado) }) {
                Text(
                    text = "Guardar",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text(
                    text = "Cancelar",
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    )
}