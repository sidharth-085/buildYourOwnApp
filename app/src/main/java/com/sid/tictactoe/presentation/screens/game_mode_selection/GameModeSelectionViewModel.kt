package com.sid.tictactoe.presentation.screens.game_mode_selection

import androidx.lifecycle.ViewModel
import com.sid.tictactoe.domain.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameModeSelectionViewModel : ViewModel() {

    // Holds the current UI state of the game mode selection screen
    private val _uiState = MutableStateFlow<UiState>(UiState.SelectMode)

    // Exposes the UI state as a read-only state flow to observe changes in the UI
    val uiState = _uiState.asStateFlow()

    // Called when the user selects a game mode (against AI or another player)
    fun onSelectModeSubmitted(isAi: Boolean) {
        // Updates the UI state to transition to the settings screen with the selected mode
        _uiState.value = UiState.Settings(isAi)
    }

    // Called when the user presses the back button
    fun onBackPressed() {
        // Resets the UI state back to mode selection
        _uiState.value = UiState.SelectMode
    }
}