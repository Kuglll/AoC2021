import java.io.File

fun main() {

    // Part 1
    val input = File("src/Inputs/Day4.txt").readLines()

    val gameboards = mutableListOf<GameBoard>()

    val drawnNumbers = input[0]

    var tempGameboard = GameBoard()
    for (line in input.drop(2)) {
        if (line == "") {
            gameboards.add(tempGameboard)
            tempGameboard = GameBoard()
            continue
        }
        val row = line.trim().replace("  ", " ").split(" ").map {
            NumberOnBoard(it)
        }
        tempGameboard.numbers.add(row)
    }

    var firstWinningBoard: GameBoard? = null
    val winningBoards = mutableListOf<GameBoard>()
    loop@ for (number in drawnNumbers.split(",")) {
        for (gameboard in gameboards) {
            gameboard.markHitNumbers(number)
            if (gameboard.isBoardWinning()) {
                if(firstWinningBoard == null){
                    firstWinningBoard = gameboard
                    println("Final score first winning board: ${gameboard.getSumOfAllUnmarkedNumbers() * number.toInt()}")
                }
                if(!winningBoards.contains(gameboard)){
                    winningBoards.add(gameboard)
                }
                if(winningBoards.size == gameboards.size){
                    // Last winning board
                    println("Final score last winning board: ${gameboard.getSumOfAllUnmarkedNumbers() * number.toInt()}")
                    break@loop
                }
            }
        }
    }
}

class GameBoard(
    val numbers: MutableList<List<NumberOnBoard>> = mutableListOf()
) {

    fun markHitNumbers(number: String) {
        numbers.forEach { row ->
            row.forEach { numberOnBoard ->
                if (numberOnBoard.number.compareTo(number) == 0) {
                    numberOnBoard.isMarked = true
                }
            }
        }
    }

    fun isBoardWinning() = isAnyRowWinning() || isAnyColumnWinning()

    private fun isAnyRowWinning(): Boolean {
        numbers.forEach { row ->
            if(row.count { it.isMarked } == row.size){
                return true
            }
        }
        return false
    }

    private fun isAnyColumnWinning(): Boolean {
        val columnStatuses = mutableListOf<Boolean>()
        for(i in 0 until numbers.size){
            columnStatuses.add(isColumnWinning(i))
        }

        return columnStatuses.any{
            it
        }
    }

    private fun isColumnWinning(index: Int): Boolean {
        numbers.forEach { row ->
            if(!row[index].isMarked){
                return false
            }
        }
        return true
    }

    fun getSumOfAllUnmarkedNumbers() =
        numbers.sumOf { row ->
            row.filter { !it.isMarked }.map { it.number.toInt() }.sum()
        }

}

class NumberOnBoard(
    val number: String,
    var isMarked: Boolean = false
)