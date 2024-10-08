package com.josuerdx.appsordomudos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josuerdx.appsordomudos.ui.theme.AppSordoMudosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSordoMudosTheme {
                // Cargar la aplicación
                AppSordoMudosApp()
                // (mike wazouzki)
            }
        }
    }
}
