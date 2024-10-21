package com.josuerdx.appsordomudos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josuerdx.appsordomudos.ui.theme.AppSordoMudosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSordoMudosTheme {
                // Aquí se carga la aplicación y se muestra el SplashScreen primero
                AppSordoMudosApp()
            }
        }
    }
}
