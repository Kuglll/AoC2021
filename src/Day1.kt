import java.io.File

fun main(){

    val input = File("src/Inputs/Day1.txt")

    //Part 1
    val numberOfIncreases = input.readLines().zip(input.readLines().drop(1)).filter {
        it.first.toInt() < it.second.toInt()
    }.count()

    println("Number of increases: $numberOfIncreases")

    //Part 2

    var index = 0
    val lines = input.readLines()
    var numberOfWindowIncreases = 0

    input.forEachLine {
        try {
            val sumFirstWindow = it.toInt() + lines.get(index + 1).toInt() + lines.get(index + 2).toInt()
            val sumSecondWindow = lines.get(index + 1).toInt() + lines.get(index + 2).toInt() + lines.get(index + 3).toInt()

            if (sumFirstWindow < sumSecondWindow){
                numberOfWindowIncreases++
            }

            index++
        } catch (indexOutOfBoundsException: IndexOutOfBoundsException){
            println("Not enough measurements left.")
        }
    }

    println("Number of window increases $numberOfWindowIncreases")



}