package com.josuerdx.appsordomudos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josuerdx.appsordomudos.ui.screens.HomeScreen
import com.josuerdx.appsordomudos.ui.screens.LoginScreen
import com.josuerdx.appsordomudos.ui.theme.screens.RegisterScreen
import com.josuerdx.appsordomudos.ui.theme.screens.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AppSordoMudosApp() {
    // Controlador de navegación
    val navController = rememberNavController()

    // LaunchedEffect
    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // Definimos las rutas de navegación
    NavHost(navController = navController, startDestination = "splash") {
        // Pantalla de Splash
        composable("splash") {
            SplashScreen()
        }
        // Pantalla de Login
        composable("login") {
            LoginScreen(
                onLoginClick = { navController.navigate("home") },
                onRegisterClick = { navController.navigate("register") }
            )
        }
        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onRegisterClick = { navController.navigate("home") }
            )
        }
        // Pantalla de Home
        composable("home") {
            HomeScreen(onUserClick = {})
        }
    }
}