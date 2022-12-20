package tictactoe

import java.lang.NumberFormatException

fun main(args: Array<String>) {
    val ticTacToeGame = TicTacToeGame()
    val board = readln()
    ticTacToeGame.initGameBoard(board)
    ticTacToeGame.showGameBoard()
    var success: Boolean

    do {
        success = try {
            val (row, cell) = readln().split("\\s+".toRegex()).map {
                it.toInt()
            }
            ticTacToeGame.markTurn(row, cell)
            ticTacToeGame.showGameBoard()
            true
        }
        catch (ex: TicTacToeGameException) {
            println(ex.message)
            false
        }
        catch (ex: NumberFormatException) {
            println("You should enter numbers!")
            false
        }
    } while (!success)
}