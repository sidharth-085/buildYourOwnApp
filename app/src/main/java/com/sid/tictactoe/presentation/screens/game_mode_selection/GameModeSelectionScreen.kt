package com.sid.tictactoe.presentation.screens.game_mode_selection

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sid.tictactoe.data.model.GameScreens
import com.sid.tictactoe.domain.model.UiState
import com.sid.tictactoe.presentation.components.BackgroundImage
import com.sid.tictactoe.presentation.components.FirstContent
import com.sid.tictactoe.presentation.components.GameTitle
import com.sid.tictactoe.presentation.components.SecondContent

@Composable
fun GameModeSelectionScreen(
    navController: NavController,
    viewModel: GameModeSelectionViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    Box {
        BackgroundImage(Modifier.fillMaxSize())
        Scaffold(containerColor = Color.Transparent) {
            if (isPortrait) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(horizontal = if (screenWidthDp > 500) (screenWidthDp * 0.15).dp else 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    GameTitle(modifier = Modifier.weight(0.50f))

                    AnimatedContent(
                        targetState = uiState,
                        modifier = Modifier.weight(0.50f)
                    ) { it ->
                        when (it) {
                            is UiState.SelectMode -> FirstContent(onSubmit = { isAi ->
                                viewModel.onSelectModeSubmitted(isAi)
                            })

                            is UiState.Settings -> SecondContent(
                                isAi = it.isAi,
                                onSubmit = { isPro, selectedAiLevel ->
                                    navController.navigate("${GameScreens.GAME.name}/$isPro/${it.isAi}/$selectedAiLevel")
                                },
                                onBackPressed = {
                                    viewModel.onBackPressed()
                                },
                            )
                        }
                    }
                }
            }
            else {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(vertical = if (screenHeightDp > 500) (screenHeightDp * 0.15).dp else 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    GameTitle(
                        modifier = Modifier
                            .weight(0.50f)
                    )

                    AnimatedContent(
                        targetState = uiState,
                        modifier = Modifier.weight(0.50f)
                    ) { it ->
                        when (it) {
                            is UiState.SelectMode -> FirstContent(onSubmit = { isAi ->
                                viewModel.onSelectModeSubmitted(isAi)
                            })

                            is UiState.Settings -> SecondContent(
                                isAi = it.isAi,
                                onSubmit = { isPro, selectedAiLevel ->
                                    navController.navigate("${GameScreens.GAME.name}/$isPro/${it.isAi}/$selectedAiLevel")
                                },
                                onBackPressed = {
                                    viewModel.onBackPressed()
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}