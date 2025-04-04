package com.sid.tictactoe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sid.tictactoe.data.model.GameScreens
import com.sid.tictactoe.presentation.screens.game.GameScreen
import com.sid.tictactoe.presentation.screens.game_mode_selection.GameModeSelectionScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = GameScreens.GAME_MODE_SELECTION.name
    ) {
        composable(GameScreens.GAME_MODE_SELECTION.name) { GameModeSelectionScreen(navController) }

        composable(
            "${GameScreens.GAME.name}/{isPro}/{isAi}/{level}",
            arguments = listOf(
                navArgument("isPro") { type = NavType.BoolType },
                navArgument("isAi") { type = NavType.BoolType },
                navArgument("level") { type = NavType.IntType },
            )
        ) { GameScreen(navController = navController) }
    }
}