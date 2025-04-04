package com.sid.tictactoe.domain.utils

object AIUtils {

    /**
     * Evaluates the current state of the Tic-Tac-Toe board and assigns a score.
     *
     * @param board The current board state as a 3x3 list of mutable lists.
     * @return 10 if 'X' has won, -10 if 'O' has won, or 0 if no winner.
     */
    fun evaluate(board: List<MutableList<Char>>): Int {
        // Check rows for a winning line
        for (row in 0..2) {
            if (board[row][0] == board[row][1] &&
                board[row][1] == board[row][2]
            ) {
                if (board[row][0] == 'X') return 10  // X wins
                if (board[row][0] == 'O') return -10 // O wins
            }
        }

        // Check columns for a winning line
        for (col in 0..2) {
            if (board[0][col] == board[1][col] &&
                board[1][col] == board[2][col]
            ) {
                if (board[0][col] == 'X') return 10  // X wins
                if (board[0][col] == 'O') return -10 // O wins
            }
        }

        // Check the main diagonal for a win
        if (board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]
        ) {
            if (board[0][0] == 'X') return 10  // X wins
            if (board[0][0] == 'O') return -10 // O wins
        }

        // Check the anti-diagonal for a win
        if (board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]
        ) {
            if (board[0][2] == 'X') return 10  // X wins
            if (board[0][2] == 'O') return -10 // O wins
        }

        // No winner found, return 0
        return 0
    }

    /**
     * Checks if there are any remaining moves on the board.
     *
     * @param board The current board state.
     * @return true if there are empty spaces left, false if the board is full.
     */
    fun isMovesLeft(board: List<MutableList<Char>>): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell == '_') return true // Empty cell found
            }
        }
        return false // No empty cells left
    }
}