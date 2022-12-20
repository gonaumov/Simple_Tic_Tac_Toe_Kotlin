package tictactoe

class TicTacToeGame {
    private val gameBoard: MutableList<MutableList<String>> = MutableList(3) {
        MutableList(3) {
            ""
        }
    }

    private val gridHasEmptyCells: Boolean
        get() {
            return gameBoard.fold(0) { acc: Int, strings: MutableList<String> ->
                acc + strings.filter { it == "_" }.size
            } > 0
        }

    private val countOfX: Int
        get() {
            return gameBoard.fold(0) { acc: Int, strings: MutableList<String> ->
                acc + strings.filter { it == "X" }.size
            }
        }

    private val countOfO: Int
        get() {
            return gameBoard.fold(0) { acc: Int, strings: MutableList<String> ->
                acc + strings.filter { it == "O" }.size
            }
        }

    private fun hasThreeHorizontal(row: Int): String {
        val firstCell = gameBoard[row][0]
        val isXorO = isXorO(firstCell)
        if (firstCell == gameBoard[row][1] && firstCell == gameBoard[row][2] && isXorO) {
            return firstCell
        }

        return ""
    }

    private fun hasThreeVertical(column: Int): String {
        val firstCell = gameBoard[0][column]
        val isXorO = isXorO(firstCell)
        if (firstCell == gameBoard[1][column] && firstCell == gameBoard[2][column] && isXorO) {
            return firstCell
        }

        return ""
    }

    private fun hasThreeDiagonal(): String {
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && isXorO(gameBoard[0][0])) {
            return gameBoard[0][0]
        } else if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[2][0] == gameBoard[0][2] && isXorO(gameBoard[2][0])) {
            return gameBoard[2][0]
        }

        return ""
    }

    private fun isXorO(cellContent: String) = listOf("X", "O").contains(cellContent)

    fun initGameBoard(input: String) {
        input.chunked(3).forEachIndexed { row: Int, r: String ->
            r.chunked(1).forEachIndexed { cell: Int, s: String ->
                gameBoard[row][cell] = s
            }
        }
    }

    fun getGameResult(): String {

        val horizontalWinners = listOf(
            hasThreeHorizontal(0),
            hasThreeHorizontal(1),
            hasThreeHorizontal(2)
        ).filter {
            isXorO(it)
        }

        val horizontal = horizontalWinners.let {
            if (it.size == 1) {
                it.first()
            } else {
                null
            }
        }

        val verticalWinners = listOf(
            hasThreeVertical(0),
            hasThreeVertical(1),
            hasThreeVertical(2)
        ).filter {
            isXorO(it)
        }

        val vertical = verticalWinners.let {
            if (it.size == 1) {
                it.first()
            } else {
                null
            }
        }

        val diagonal = hasThreeDiagonal().let {
            if (it == "") {
                null
            } else {
                it
            }
        }

        val isThereNoThree = horizontal == null && vertical == null && diagonal == null

        val winner = listOfNotNull(horizontal, vertical, diagonal).let {
            if (it.size == 1) {
                it.first()
            } else {
                null
            }
        }

        val difference = maxOf(countOfO, countOfX) - minOf(countOfO, countOfX)

        return when {
            verticalWinners.size > 1 || horizontalWinners.size > 1 || difference >= 2 -> "Impossible"
            isThereNoThree && gridHasEmptyCells -> "Game not finished"
            isThereNoThree && !gridHasEmptyCells -> "Draw"
            winner != null -> "$winner wins"
            else -> "Impossible"
        }
    }

    fun markTurn(inputRow: Int, inputCell: Int) {
        val properCoordinates = listOf(1,2,3)
        if (!properCoordinates.contains(inputRow) || !properCoordinates.contains(inputCell)) {
            throw TicTacToeGameException("Coordinates should be from 1 to 3!")
        }

        val row = inputRow - 1
        val cell = inputCell - 1
        if (gameBoard[row][cell] != "_") {
            throw TicTacToeGameException("This cell is occupied! Choose another one!")
        }
        gameBoard[row][cell] = "X"
    }

    fun showGameBoard() {
        println("---------")
        for (i in gameBoard) {
            print("| ")
            for (j in i) {
                print("$j ")
            }
            println("|")
        }
        println("---------")
    }
}