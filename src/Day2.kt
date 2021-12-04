import java.io.File

fun main() {

    val input = File("src/Inputs/Day2.txt")

    var horizontalPosition = 0
    var depth = 0

    //Part 1
    input.readLines().forEach {
        val (command, amount) = it.split(" ")
        when (command) {
            "forward" -> horizontalPosition += amount.toInt()
            "up" -> depth -= amount.toInt()
            "down"-> depth += amount.toInt()
        }
    }

    println("Multiplied horizontal position and depth: ${horizontalPosition * depth}")


    var aim = 0
    horizontalPosition = 0
    depth = 0
    //Part 2
    input.readLines().forEach {
        val (command, amount) = it.split(" ")
        when (command) {
            "forward" -> {
                horizontalPosition += amount.toInt()
                depth += amount.toInt() * aim
            }
            "up" -> aim -= amount.toInt()
            "down"-> aim += amount.toInt()
        }
    }

    println("Multiplied horizontal position and depth part2: ${horizontalPosition * depth}")

}