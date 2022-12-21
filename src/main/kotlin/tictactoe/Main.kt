package tictactoe

import java.lang.NumberFormatException

fun main(args: Array<String>) {
    val ticTacToeGame = TicTacToeGame()
    ticTacToeGame.showGameBoard()

    do {
        try {
            val (row, cell) = readln().split("\\s+".toRegex()).map {
                it.toInt()
            }
            ticTacToeGame.markTurn(row, cell)
            true
        } catch (ex: TicTacToeGameException) {
            println(ex.message)
            false
        } catch (ex: NumberFormatException) {
            println("You should enter numbers!")
            false
        }.let {
            if (it) {
                ticTacToeGame.showGameBoard()
            }
        }
    } while (ticTacToeGame.gameStatus == GameStatus.GAME_NOT_FINISHED)

    println(
        if (ticTacToeGame.gameStatus == GameStatus.HAS_WINNER) {
            String.format(ticTacToeGame.gameStatus.message, ticTacToeGame.winner)
        } else {
            ticTacToeGame.gameStatus.message
        }
    )
}