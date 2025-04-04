package com.sid.tictactoe.domain.utils

import com.sid.tictactoe.domain.model.Move

class ProTicTacToeAi(
    var board: List<MutableList<Char>>,
    var numberOfMoves: Int,
    var moves: ArrayDeque<Move>,
    private val maxDepth: Int
) {

    /**
     * Minimax algorithm to find the best possible move for AI ('X')
     * @param inputBoard: The current board state
     * @param inputMoves: Queue of recent moves
     * @param depth: Current recursion depth
     * @param isAI: Boolean indicating if it's AI's turn
     * @return The evaluation score of the board
     */
    private fun minimax(
        inputBoard: List<MutableList<Char>>, inputMoves: ArrayDeque<Move>, depth: Int, isAI: Boolean
    ): Int {
        var myBoard = deepCopyBoard(inputBoard)
        var myMoves = deepCopyMoves(inputMoves)
        val score = AIUtils.evaluate(myBoard)

        // Base cases: check if terminal state is reached
        if (depth >= maxDepth) return score
        if (score == 10) return score - depth // AI wins
        if (score == -10) return score + depth // Opponent wins
        if (AIUtils.isMovesLeft(myBoard).not()) return 0 // Draw

        // AI's turn - Maximizing player ('X')
        if (isAI) {
            var best = Int.MIN_VALUE
            for (i in 0..2) {
                for (j in 0..2) {
                    if (myBoard[i][j] == '_') {
                        // Make the move
                        myBoard[i][j] = 'X'
                        numberOfMoves++
                        myMoves.add(Move(i, j))

                        // Maintain only the last 6 moves
                        if (numberOfMoves > 6) {
                            val deletedMove = myMoves.removeFirst()
                            myBoard[deletedMove.row][deletedMove.column] = '_'
                        }

                        // Recursively call minimax and get the best score
                        best = maxOf(best, minimax(myBoard, myMoves, depth + 1, false))

                        // Undo the move
                        myBoard = deepCopyBoard(inputBoard)
                        myMoves = deepCopyMoves(inputMoves)
                        numberOfMoves--
                    }
                }
            }
            return best
        }
        // Opponent's turn - Minimizing player ('O')
        else {
            var best = Int.MAX_VALUE
            for (i in 0..2) {
                for (j in 0..2) {
                    if (myBoard[i][j] == '_') {
                        // Make the move
                        myBoard[i][j] = 'O'
                        numberOfMoves++
                        myMoves.add(Move(i, j))

                        // Maintain only the last 6 moves
                        if (numberOfMoves > 6) {
                            val deletedMove = myMoves.removeFirst()
                            myBoard[deletedMove.row][deletedMove.column] = '_'
                        }

                        // Recursively call minimax and get the worst score
                        best = minOf(best, minimax(myBoard, myMoves, depth + 1, true))

                        // Undo the move
                        myBoard = deepCopyBoard(inputBoard)
                        myMoves = deepCopyMoves(inputMoves)
                        numberOfMoves--
                    }
                }
            }
            return best
        }
    }

    /**
     * Determines the best move for the AI ('X')
     * @return The best possible move
     */
    fun findBestMove(): Move {
        var myBoard = deepCopyBoard(board)
        var myMoves = deepCopyMoves(moves)
        var bestVal = Int.MIN_VALUE
        var bestMove = Move(-1, -1) // Default invalid move

        // Iterate over all available spots
        for (i in 0..2) {
            for (j in 0..2) {
                if (myBoard[i][j] == '_') {
                    // Try placing 'X' and compute its score
                    myBoard[i][j] = 'X'
                    numberOfMoves++
                    myMoves.add(Move(i, j))

                    // Maintain only the last 6 moves
                    if (numberOfMoves > 6) {
                        val deletedMove = myMoves.removeFirst()
                        myBoard[deletedMove.row][deletedMove.column] = '_'
                    }

                    val moveVal = minimax(myBoard, myMoves, 0, false)

                    // Undo the move
                    myBoard = deepCopyBoard(board)
                    myMoves = deepCopyMoves(moves)
                    numberOfMoves--

                    // Update the best move if found a higher score
                    if (moveVal > bestVal) {
                        bestMove = Move(i, j)
                        bestVal = moveVal
                    }
                }
            }
        }
        return bestMove
    }

    /**
     * Creates a deep copy of the board
     */
    private fun deepCopyBoard(board: List<MutableList<Char>>): List<MutableList<Char>> {
        return board.map { it.toMutableList() }
    }

    /**
     * Creates a deep copy of the moves queue
     */
    private fun deepCopyMoves(moves: ArrayDeque<Move>): ArrayDeque<Move> {
        return ArrayDeque(moves.map { Move(it.row, it.column) })
    }
}