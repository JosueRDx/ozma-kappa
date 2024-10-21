package com.josuerdx.appsordomudos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josuerdx.appsordomudos.screens.HomeScreen
import com.josuerdx.appsordomudos.screens.login.LoginScreen
import com.josuerdx.appsordomudos.screens.ProfileScreen
import com.josuerdx.appsordomudos.screens.sign_up.RegisterScreen
import com.josuerdx.appsordomudos.screens.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AppSordoMudosApp() {
    val navController = rememberNavController()

    // Mostrar el SplashScreen durante 2 segundos
    LaunchedEffect(Unit) {
        delay(2000)  // Duración del splash en milisegundos
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // Configurar el NavHost para manejar la navegación entre pantallas
    NavHost(navController = navController, startDestination = "splash") {
        // Pantalla de Splash
        composable("splash") {
            SplashScreen()  // Aquí se muestra tu SplashScreen personalizado
        }

        // Pantalla de Login
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Home
        composable("home") {
            HomeScreen(
                onUserClick = {
                    navController.navigate("profile")
                },
                onHomeClick = { /* Acción de Home */ },
                onMenuClick = { /* Acción de Menú */ }
            )
        }

        // Pantalla de Perfil
        composable("profile") {
            ProfileScreen(
                onCloseClick = {
                    navController.popBackStack()
                },
                onHomeClick = {
                    navController.navigate("home")
                }
            )
        }
    }
}
