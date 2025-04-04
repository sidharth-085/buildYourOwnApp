package com.sid.tictactoe.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sid.tictactoe.presentation.navigation.AppNavHost
import com.sid.tictactoe.presentation.theme.TicTacToeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var keepOnScreenCondition = mutableStateOf(true)
        installSplashScreen().setKeepOnScreenCondition {
            keepOnScreenCondition.value
        }
        setContent {
            LaunchedEffect(null) {
                delay(1000L)
                keepOnScreenCondition.value = false
            }
            TicTacToeTheme { AppNavHost() }
        }
    }
}