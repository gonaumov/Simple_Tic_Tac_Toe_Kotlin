package tictactoe

fun main(args: Array<String>) {
    val ticTacToeGame = TicTacToeGame()
    val board = readln()
    ticTacToeGame.initGameBoard(board)
    ticTacToeGame.showGameBoard()
    println(ticTacToeGame.getGameResult())
}