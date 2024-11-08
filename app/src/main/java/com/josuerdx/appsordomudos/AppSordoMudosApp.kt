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
                    navController.navigate("home") // Mantente en Home si ya estás en Home
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
                    navController.navigate("addsign") // Navega a la pantalla de agregar seña
                },
                onTranslatorClick = {
                    // Aquí puedes navegar a la pantalla de Traductor
                },
                onMyGesturesClick = {
                    // Aquí puedes navegar a la pantalla de Mis Gestos
                },
                onInfoClick = {
                    // Aquí puedes navegar a la pantalla de Información
                },
                onHomeClick = { navController.navigate("home") },    // Navega a Home
                onUserClick = { navController.navigate("profile") }, // Navega a Perfil
                onMenuClick = { navController.navigate("options") } // Mantente en Opciones
            )
        }

        // Pantalla de Agregar Seña
        composable("addsign") {
            AddSignScreen(
                onSaveClick = { navController.popBackStack() }, // Navega de regreso después de guardar
                onHomeClick = { navController.navigate("home") },    // Navega a Home
                onUserClick = { navController.navigate("profile") }, // Navega a Perfil
                onMenuClick = { navController.navigate("options") } // Navega a Opciones
            )
        }
    }
}
