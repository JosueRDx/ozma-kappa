package com.josuerdx.appsordomudos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.josuerdx.appsordomudos.ui.theme.screens.HomeScreen
import com.josuerdx.appsordomudos.ui.theme.screens.LoginScreen
import com.josuerdx.appsordomudos.ui.theme.screens.ProfileScreen
import com.josuerdx.appsordomudos.ui.theme.screens.RegisterScreen
import com.josuerdx.appsordomudos.ui.theme.screens.SplashScreen
import kotlinx.coroutines.delay

// Definimos una clase UserData que tiene los campos necesarios
data class UserData(
    val nombre: String,
    val apellido: String,
    val correo: String,
    val direccion: String
)

@Composable
fun AppSordoMudosApp() {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        delay(1000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen() }

        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onLoginError = { /* Manejar error */ },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterClick = { user ->
                    // Después de registrar al usuario, redirigir al login
                    navController.navigate("login?correo=${user.correo}")
                }
            )
        }

        composable("home") {
            HomeScreen(
                onUserClick = {
                    // Suponiendo que tienes un objeto de usuario aquí
                    val dummyUser = UserData("Juan", "Pérez", "juan.perez@example.com", "Calle Falsa 123")
                    navController.navigate("profile?nombre=${dummyUser.nombre}&apellido=${dummyUser.apellido}&correo=${dummyUser.correo}&direccion=${dummyUser.direccion}")
                },
                onHomeClick = { /* Acción de inicio */ },
                onMenuClick = { /* Acción del menú */ }
            )
        }

        composable(
            "profile?nombre={nombre}&apellido={apellido}&correo={correo}&direccion={direccion}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("correo") { type = NavType.StringType },
                navArgument("direccion") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // Recuperar los datos del usuario de los argumentos
            val nombre = backStackEntry.arguments?.getString("nombre")
            val apellido = backStackEntry.arguments?.getString("apellido")
            val correo = backStackEntry.arguments?.getString("correo")
            val direccion = backStackEntry.arguments?.getString("direccion")

            // Asegurarse de que no sean nulos antes de crear el usuario
            if (nombre != null && apellido != null && correo != null && direccion != null) {
                val user = UserData(nombre, apellido, correo, direccion)

                // Pasa el usuario a ProfileScreen
                ProfileScreen(
                    user = user,
                    onCloseClick = { navController.popBackStack() },
                    onHomeClick = { navController.navigate("home") },
                    onUserClick = { navController.navigate("profile") }, // Aquí se agrega el onUserClick
                    onMenuClick = { /* Aquí la acción para el menú */ } // Aquí puedes definir la acción del menú
                )
            }
        }
    }
}
