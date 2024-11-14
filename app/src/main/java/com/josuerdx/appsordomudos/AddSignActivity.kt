package com.josuerdx.appsordomudos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.josuerdx.appsordomudos.screens.addsign.AddSignScreen

class AddSignActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddSignScreen()
        }
    }
}