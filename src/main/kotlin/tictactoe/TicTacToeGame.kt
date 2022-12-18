package tictactoe

class TicTacToeGame {
    private val gameBoard: MutableList<MutableList<String>> = MutableList(3) {
        MutableList(3) {
            ""
        }
    }

    fun initGameBoard(input: String) {
        input.chunked(3).forEachIndexed {
                row: Int, r: String ->
            r.chunked(1).forEachIndexed {
                    cell: Int, s: String ->
                gameBoard[row][cell] = s
            }
        }
    }

    fun showGameBoard() {
        println("---------")
        for (i in gameBoard) {
            print("| ")
            for(j in i) {
                print("$j ")
            }
            println("|")
        }
        println("---------")
    }
}