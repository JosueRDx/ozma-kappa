// AppSordoMudosApp.kt
package com.josuerdx.appsordomudos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josuerdx.appsordomudos.screens.home.HomeScreen
import com.josuerdx.appsordomudos.screens.login.LoginScreen
import com.josuerdx.appsordomudos.screens.ProfileScreen
import com.josuerdx.appsordomudos.screens.sign_up.RegisterScreen
import com.josuerdx.appsordomudos.screens.options.OptionsScreen
import com.josuerdx.appsordomudos.screens.addsign.AddSignScreen
import com.josuerdx.appsordomudos.screens.gestures.GesturesScreen
import com.josuerdx.appsordomudos.screens.information.InformationScreen
import com.josuerdx.appsordomudos.screens.settings.SettingsScreen

@Composable
fun AppSordoMudosApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
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
                onHomeClick = {
                    navController.navigate("home")
                },
                onMenuClick = {
                    navController.navigate("options")
                }
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

        // Pantalla de Opciones
        composable("options") {
            OptionsScreen(
                onAddSignClick = {
                    navController.navigate("addsign")
                },
                onTranslatorClick = {
                    // Aquí puedes navegar a la pantalla de Traductor
                },
                onMyGesturesClick = {
                    navController.navigate("gestures")
                },
                onInfoClick = {
                    navController.navigate("information") // Nueva pantalla de Información
                },
                onHomeClick = { navController.navigate("home") },
                onUserClick = { navController.navigate("profile") },
                onMenuClick = { navController.navigate("options") }
            )
        }

        // Pantalla de Agregar Seña
        composable("addsign") {
            AddSignScreen(
                onSaveClick = { navController.popBackStack() },
                onHomeClick = { navController.navigate("home") },
                onUserClick = { navController.navigate("profile") },
                onMenuClick = { navController.navigate("options") }
            )
        }

        // Pantalla de Mis Gestos
        composable("gestures") {
            GesturesScreen(
                onHomeClick = { navController.navigate("home") },
                onUserClick = { navController.navigate("profile") },
                onMenuClick = { navController.navigate("options") }
            )
        }

        // Pantalla de Información
        composable("information") {
            InformationScreen(
                onHomeClick = { navController.navigate("home") },
                onUserClick = { navController.navigate("profile") },
                onMenuClick = { navController.navigate("options") },
                onBackClick = { navController.popBackStack() }
            )
        }

        // Pantalla de Configuraciones
        composable("settings") {
            SettingsScreen(
                onLogoutClick = { /* Acción para cerrar sesión */ },
                onHomeClick = { navController.navigate("home") },
                onUserClick = { navController.navigate("profile") },
                onMenuClick = { navController.navigate("options") }
            )
        }
    }
}
