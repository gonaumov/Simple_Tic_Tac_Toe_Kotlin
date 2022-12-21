package tictactoe

enum class GameStatus(val message: String) {
    IMPOSSIBLE("Impossible"),
    GAME_NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    HAS_WINNER("%s wins")
}