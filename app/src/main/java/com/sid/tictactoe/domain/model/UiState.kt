package com.sid.tictactoe.domain.model

sealed class UiState {
    data object SelectMode : UiState()
    data class Settings(var isAi: Boolean) : UiState()
}