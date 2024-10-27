package com.josuerdx.appsordomudos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josuerdx.appsordomudos.ui.theme.AppSordoMudosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppSordoMudos)  // Cambiar al tema principal antes de setContent
        super.onCreate(savedInstanceState)
        setContent {
            AppSordoMudosTheme {
                AppSordoMudosApp()  // El contenido de tu aplicación
            }
        }
    }
}
