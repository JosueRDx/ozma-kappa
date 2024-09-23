package com.josuerdx.appsordomudos.ui.screens

import User
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.database.AppDatabase
import kotlinx.coroutines.launch

@Composable
fun UserAdminScreen(db: AppDatabase) {
    val scope = rememberCoroutineScope()
    var users by remember { mutableStateOf(listOf<User>()) }
    var loading by remember { mutableStateOf(true) }

    // Cargar usuarios
    LaunchedEffect(Unit) {
        loading = true
        users = db.userDao().getAllUsers()
        loading = false
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Usuarios Registrados", fontSize = 24.sp, color = Color.Black)

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(users) { user ->
                        UserItem(
                            user = user,
                            onEdit = {
                                // Navegar a la pantalla de editar usuario
                            },
                            onDelete = {
                                scope.launch {
                                    db.userDao().deleteUser(user) // Función de eliminación
                                    users = db.userDao().getAllUsers() // Actualizar la lista
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${user.name}", fontWeight = FontWeight.Bold)
            Text(text = "Email: ${user.email}")

            Row {
                Button(onClick = onEdit) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete) {
                    Text("Eliminar")
                }
            }
        }
    }
}
