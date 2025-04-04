package com.sid.tictactoe.domain.utils

import com.sid.tictactoe.domain.model.Move

class TicTacToeAi(private val maxDepth: Int) {

    /**
     * Minimax algorithm to evaluate the best move for AI.
     * @param board The current game board.
     * @param depth The current depth of recursion.
     * @param isAI A boolean indicating whether it's AI's turn.
     * @return The best score possible for the AI.
     */
    private fun minimax(
        board: List<MutableList<Char>>,
        depth: Int,
        isAI: Boolean
    ): Int {
        val score = AIUtils.evaluate(board)

        // Base cases: If a terminal state is reached, return the score
        if (depth >= maxDepth) return score
        if (score == 10) return score - depth  // AI win (subtract depth for faster wins)
        if (score == -10) return score + depth // Opponent win (add depth for delayed loss)
        if (!AIUtils.isMovesLeft(board)) return 0 // Draw

        return if (isAI) {
            // Maximizing for AI ('X')
            var best = Int.MIN_VALUE

            for (i in 0..2) {
                for (j in 0..2) {
                    if (board[i][j] == '_') {
                        // Try placing 'X' and evaluate the board
                        board[i][j] = 'X'
                        best = maxOf(best, minimax(board, depth + 1, false))
                        // Undo the move
                        board[i][j] = '_'
                    }
                }
            }
            best
        } else {
            // Minimizing for opponent ('O')
            var best = Int.MAX_VALUE

            for (i in 0..2) {
                for (j in 0..2) {
                    if (board[i][j] == '_') {
                        // Try placing 'O' and evaluate the board
                        board[i][j] = 'O'
                        best = minOf(best, minimax(board, depth + 1, true))
                        // Undo the move
                        board[i][j] = '_'
                    }
                }
            }
            best
        }
    }

    /**
     * Finds the best move for the AI on the current board.
     * @param board The current game board.
     * @return The best possible move for AI.
     */
    fun findBestMove(board: List<MutableList<Char>>): Move {
        var bestVal = Int.MIN_VALUE
        var bestMove = Move(-1, -1) // Default move (invalid)

        // Iterate through all empty cells to find the best move
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == '_') {
                    // Try placing 'X' and evaluate the board
                    board[i][j] = 'X'
                    val moveVal = minimax(board, 0, false)
                    // Undo the move
                    board[i][j] = '_'

                    // Update best move if a better one is found
                    if (moveVal > bestVal) {
                        bestMove = Move(i, j)
                        bestVal = moveVal
                    }
                }
            }
        }
        return bestMove
    }
}