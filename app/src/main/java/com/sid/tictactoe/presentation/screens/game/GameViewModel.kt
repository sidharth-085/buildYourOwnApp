package com.sid.tictactoe.presentation.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sid.tictactoe.R
import com.sid.tictactoe.domain.utils.ProTicTacToeAi
import com.sid.tictactoe.domain.utils.TicTacToeAi
import com.sid.tictactoe.data.model.AILevel
import com.sid.tictactoe.domain.model.Move
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Retrieve game settings from saved state
    val isPro = savedStateHandle.get<Boolean>("isPro")!!
    val isAi = savedStateHandle.get<Boolean>("isAi")!!

    // Determine AI difficulty level if AI mode is enabled
    private val aiLevel: AILevel? =
        if (savedStateHandle.get<Boolean>("isAi")!!) AILevel.entries[savedStateHandle.get<Int>("level")!!]
        else null

    private var _turnNumber = 0 // Tracks the number of moves made
    private var _xoQueue: ArrayDeque<Move> = ArrayDeque() // Queue for tracking moves in Pro mode

    private val _isTurnX = MutableStateFlow(false) // Tracks the current player's turn
    val isTurnX = _isTurnX.asStateFlow()

    private val _isGameFinished = MutableStateFlow(false) // Tracks if the game has ended
    val isGameFinished = _isGameFinished.asStateFlow()

    private val _winnerTitle = MutableStateFlow<Int?>(null) // Holds the winner text resource ID
    val winnerTitle = _winnerTitle.asStateFlow()

    private val _xWins = MutableStateFlow(0) // Number of X's wins
    val xWins = _xWins.asStateFlow()

    private val _oWins = MutableStateFlow(0) // Number of O's wins
    val oWins = _oWins.asStateFlow()

    private val _xoList = MutableStateFlow(List(3) { MutableList(3) { '_' } }) // Game board state
    val xoList = _xoList.asStateFlow()

    private val _isGoingToDeleteList = MutableStateFlow(List(3) { MutableList(3) { false } })
    val isGoingToDeleteList = _isGoingToDeleteList.asStateFlow()

    // Function to handle a player's move
    suspend fun performNewMove(move: Move) {
        updateXOList(
            row = move.row, col = move.column, value = if (_isTurnX.value) 'X' else 'O'
        )

        if (isPro) {
            updateIsGoingToDeleteList(row = move.row, col = move.column, value = false)

            // Manage the queue of moves in Pro mode
            if (_turnNumber < 7) {
                _turnNumber++
            }
            _xoQueue.add(move)

            // Remove oldest move if more than 6 moves are present
            if (_turnNumber > 6) {
                updateXOList(row = _xoQueue.first().row, col = _xoQueue.first().column, value = '_')
                _xoQueue.removeFirst()
            }
            if (_turnNumber > 5) {
                updateIsGoingToDeleteList(row = _xoQueue.first().row, col = _xoQueue.first().column, value = true)
            }
        }

        checkGameStatus() // Check if the game is over
        _isTurnX.value = !_isTurnX.value // Switch turn

        if (isAi) performAiMove() // Trigger AI move if applicable
    }

    // Function to let AI make its move
    private suspend fun performAiMove() {
        if (!_isGameFinished.value) {
            delay(1000) // Simulate AI thinking time
            val bestMoveAi = withContext(Dispatchers.Default) {
                if (isPro) ProTicTacToeAi(
                    numberOfMoves = _turnNumber,
                    moves = _xoQueue,
                    board = _xoList.value,
                    maxDepth = aiLevel!!.depth
                ).findBestMove()
                else TicTacToeAi(maxDepth = aiLevel!!.depth).findBestMove(board = _xoList.value)
            }

            updateXOList(row = bestMoveAi.row, col = bestMoveAi.column, value = 'X')

            if (isPro) {
                updateIsGoingToDeleteList(row = bestMoveAi.row, col = bestMoveAi.column, value = false)
                if (_turnNumber < 7) {
                    _turnNumber++
                }
                _xoQueue.add(bestMoveAi)

                // Remove oldest move in Pro mode
                if (_turnNumber > 6) {
                    updateXOList(row = _xoQueue.first().row, col = _xoQueue.first().column, value = '_')
                    _xoQueue.removeFirst()
                }
                if (_turnNumber > 5) {
                    updateIsGoingToDeleteList(row = _xoQueue.first().row, col = _xoQueue.first().column, value = true)
                }
            }
            checkGameStatus() // Check if the game has ended
            _isTurnX.value = !_isTurnX.value // Switch turn
        }
    }

    // Function to update the game board
    private fun updateXOList(row: Int, col: Int, value: Char) {
        val currentList = _xoList.value.map { it.toMutableList() }
        currentList[row][col] = value
        _xoList.value = currentList
    }

    // Function to track which move is going to be deleted in Pro mode
    private fun updateIsGoingToDeleteList(row: Int, col: Int, value: Boolean) {
        val currentList = _isGoingToDeleteList.value.map { it.toMutableList() }
        currentList[row][col] = value
        _isGoingToDeleteList.value = currentList
    }

    // Check if the game has ended (win or draw)
    private fun checkGameStatus() {
        // Check rows for a win
        for (row in 0..2) {
            if (_xoList.value[row][0] == _xoList.value[row][1] && _xoList.value[row][1] == _xoList.value[row][2] && _xoList.value[row][0] != '_') {
                _isGameFinished.value = true
                placeWinner(_xoList.value[row][0])
                return
            }
        }

        // Check columns for a win
        for (col in 0..2) {
            if (_xoList.value[0][col] == _xoList.value[1][col] && _xoList.value[1][col] == _xoList.value[2][col] && _xoList.value[0][col] != '_') {
                _isGameFinished.value = true
                placeWinner(_xoList.value[0][col])
                return
            }
        }

        // Check diagonals for a win
        if (_xoList.value[0][0] == _xoList.value[1][1] && _xoList.value[1][1] == _xoList.value[2][2] && _xoList.value[0][0] != '_') {
            _isGameFinished.value = true
            placeWinner(_xoList.value[0][0])
            return
        }

        if (_xoList.value[0][2] == _xoList.value[1][1] && _xoList.value[1][1] == _xoList.value[2][0] && _xoList.value[0][2] != '_') {
            _isGameFinished.value = true
            placeWinner(_xoList.value[0][2])
            return
        }

        // Check if board is full (draw)
        if (_xoList.value.all { row -> row.all { it != '_' } }) {
            _isGameFinished.value = true
            placeWinner(null)
        }
    }

    // Handle assigning the winner
    private fun placeWinner(winner: Char?) {
        when (winner) {
            'X' -> {
                _winnerTitle.value = if (isAi) R.string.you_lost else R.string.winner_is_x
                _xWins.value++
            }
            'O' -> {
                _winnerTitle.value = if (isAi) R.string.you_won else R.string.winner_is_o
                _oWins.value++
            }
            null -> {
                _winnerTitle.value = R.string.draw_play_again
                _xWins.value++
                _oWins.value++
            }
        }
    }

    // Reset the game state
    suspend fun resetGame() {
        _isGameFinished.value = false
        clearGame()
        _winnerTitle.value = null
        if (isAi && _isTurnX.value) performAiMove()
    }

    // Clears the board and resets turn tracking
    private fun clearGame() {
        _xoList.value = MutableList(3) { MutableList(3) { '_' } }
        _xoQueue.clear()
        _turnNumber = 0
    }
}