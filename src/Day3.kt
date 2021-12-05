import java.io.File

fun main(){

    // Part 1
    val input = File("src/Inputs/Day3.txt")

    val inputLineLength = input.readLines()[0].length

    val listForEachDigitPosition = mutableListOf<MutableList<Char>>()

    for(i in 0..inputLineLength - 1){
        val listOfDigits = mutableListOf<Char>()
        input.forEachLine { inputLine ->
            listOfDigits.add(inputLine.get(i))
        }
        listForEachDigitPosition.add(listOfDigits)
    }

    var gammaRate = ""
    for(list in listForEachDigitPosition){
        val (one, zero) = list.partition { digit -> digit.compareTo('1') == 0 }
        gammaRate += (if(one.size > zero.size) "1" else "0")
    }

    var epsilonRate = ""
    gammaRate.forEach {
        epsilonRate += if(it.compareTo('1') == 0) "0" else "1"
    }

    println("Power consumption of the submarine: ${gammaRate.toInt(2) * epsilonRate.toInt(2)}")

    //Part 2

}