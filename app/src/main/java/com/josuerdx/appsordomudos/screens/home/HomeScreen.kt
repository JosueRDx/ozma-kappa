package com.josuerdx.appsordomudos.screens.home

import HomeIconItem
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josuerdx.appsordomudos.R
import com.josuerdx.appsordomudos.components.CustomTabBar
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onHomeClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Título centrado
            Text(
                text = "EchoHands",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
            )

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(80.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(90.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(100.dp)) {
                        HomeIconItem(
                            iconRes = R.drawable.signal,
                            onClick = { viewModel.onConnectionClick() }
                        )
                        HomeIconItem(
                            iconRes = R.drawable.deep_learning,
                            onClick = { viewModel.onLearningClick() }
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(150.dp)) {
                        HomeIconItem(
                            iconRes = R.drawable.settings,
                            onClick = { viewModel.onSettingsClick() }
                        )
                        HomeIconItem(
                            iconRes = R.drawable.add,
                            onClick = { viewModel.onAddClick() }
                        )
                    }

                    HomeIconItem(
                        iconRes = R.drawable.hhome,
                        onClick = { viewModel.onTranslateClick() }
                    )
                }
            }

            // TabBar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                CustomTabBar(
                    onHomeClick = onHomeClick,
                    onUserClick = onUserClick,
                    onMenuClick = onMenuClick
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
