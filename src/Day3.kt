import java.io.File

fun main() {

    // Part 1
    val input = File("src/Inputs/Day3.txt")

    val inputLineLength = input.readLines()[0].length

    var listForEachDigitPosition = mutableListOf<MutableList<Char>>()

    for (i in 0 until inputLineLength) {
        val listOfDigits = mutableListOf<Char>()
        input.forEachLine { inputLine ->
            listOfDigits.add(inputLine.get(i))
        }
        listForEachDigitPosition.add(listOfDigits)
    }

    var gammaRate = ""
    for (list in listForEachDigitPosition) {
        val (one, zero) = list.partition { digit -> digit.compareTo('1') == 0 }
        gammaRate += (if (one.size > zero.size) "1" else "0")
    }

    var epsilonRate = ""
    gammaRate.forEach {
        epsilonRate += if (it.compareTo('1') == 0) "0" else "1"
    }

    println("The power consumption of the submarine: ${gammaRate.toInt(2) * epsilonRate.toInt(2)}")

    //Part 2
    var oxygenGeneratorRating = input.readLines()

    for (i in 0 until inputLineLength) {
        val (zeroes, ones) = countOnesAndZeroes(oxygenGeneratorRating, i)
        if (ones >= zeroes) {
            oxygenGeneratorRating = oxygenGeneratorRating.filter { it[i].compareTo('1') == 0 }
        } else {
            oxygenGeneratorRating = oxygenGeneratorRating.filter { it[i].compareTo('0') == 0 }
        }
    }

    var co2ScrubberRating = input.readLines()
    for (i in 0 until inputLineLength) {
        val (zeroes, ones) = countOnesAndZeroes(co2ScrubberRating, i)
        if (zeroes <= ones) {
            co2ScrubberRating = co2ScrubberRating.filter { it[i].compareTo('0') == 0 }
        } else {
            co2ScrubberRating = co2ScrubberRating.filter { it[i].compareTo('1') == 0 }
        }
        if(co2ScrubberRating.size == 1){
            break
        }
    }

    println("The life support rating of the submarine: ${oxygenGeneratorRating[0].toInt(2) * co2ScrubberRating[0].toInt(2)}")

}

fun countOnesAndZeroes(input: List<String>, digitIndex: Int): Pair<Int, Int> {
    var zeroes = 0
    var ones = 0
    input.forEach { inputLine ->
        if (inputLine[digitIndex].compareTo('1') == 0) ones++ else zeroes++
    }
    return Pair(zeroes, ones)
}