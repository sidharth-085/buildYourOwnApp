package com.sid.tictactoe.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sid.tictactoe.R
import com.sid.tictactoe.data.model.GameScreens
import com.sid.tictactoe.presentation.screens.game.GameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainLayout(
    viewModel: GameViewModel,
    modifier: Modifier,
    navController: NavController,
) {
    val isTurnX by viewModel.isTurnX.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(
                visible = if (viewModel.isAi.not()) isTurnX else false,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        stringResource(R.string.your_move),
                        style = MaterialTheme.typography.bodyLarge.plus(
                            TextStyle(
                                fontSize = 20.sp,
                            )
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.rotate(180f)
                    )

                    Text(
                        if (isTurnX) "X" else (if (viewModel.isAi) "O" else "X"),
                        style = MaterialTheme.typography.headlineMedium.plus(
                            TextStyle(
                                fontSize = 50.sp
                            )
                        ),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
        Board(
            viewModel = viewModel,
            modifier = Modifier.align(Alignment.Center)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = isTurnX.not() or viewModel.isAi,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        if (isTurnX) (if (viewModel.isAi) "X" else "O") else "O",
                        style = MaterialTheme.typography.headlineMedium.plus(
                            TextStyle(
                                fontSize = 50.sp
                            )
                        ),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        if (viewModel.isAi.not()) "Your Move" else (if (isTurnX) stringResource(R.string.ai_s_move) else stringResource(R.string.your_move)),
                        style = MaterialTheme.typography.bodyLarge.plus(
                            TextStyle(
                                fontSize = 20.sp,
                            )
                        ),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(
                    start = 20.dp,
                    top = 20.dp
                )
        ) {
            MiniCustomButton(
                icon = Icons.Filled.Close,
                onClick = {
                    navController.navigate(GameScreens.GAME_MODE_SELECTION.name) {
                        popUpTo(0) { inclusive = true }
                    }
                },
            )
            Spacer(Modifier.height(15.dp))
            MiniCustomButton(
                icon = Icons.Filled.Replay,
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (viewModel.isAi.not() or (viewModel.isAi and isTurnX.not())) viewModel.resetGame()
                    }
                },
            )
        }
    }
}