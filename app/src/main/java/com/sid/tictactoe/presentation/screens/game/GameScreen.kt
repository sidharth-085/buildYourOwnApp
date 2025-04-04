package com.sid.tictactoe.presentation.screens.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sid.tictactoe.R
import com.sid.tictactoe.presentation.components.MiniCustomButton
import com.sid.tictactoe.data.model.GameScreens
import com.sid.tictactoe.presentation.components.MainLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    navController: NavController,
) {
    val isGameFinished by gameViewModel.isGameFinished.collectAsState()
    val winnerTitle by gameViewModel.winnerTitle.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            MainLayout(
                modifier = Modifier.padding(innerPadding),
                viewModel = gameViewModel,
                navController = navController
            )

            AnimatedVisibility(
                visible = isGameFinished,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { }
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.75f))
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(winnerTitle ?: R.string.empty_string),
                        style = MaterialTheme.typography.headlineMedium.plus(
                            TextStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 50.sp,
                            )
                        ),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        MiniCustomButton(
                            onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    gameViewModel.resetGame()
                                }
                            },
                            icon = Icons.Filled.Replay,
                        )
                        Spacer(Modifier.width(24.dp))
                        MiniCustomButton(
                            onClick = {
                                navController.navigate(GameScreens.GAME_MODE_SELECTION.name) {
                                    popUpTo(0) { inclusive = true }
                                }
                            },
                            icon = Icons.Filled.Home,
                        )
                    }
                }
            }
        }
    }
}