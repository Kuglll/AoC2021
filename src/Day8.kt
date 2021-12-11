import java.io.File

fun main(){

    val input = File("src/Inputs/Day8.txt")

    // Part 1
    var numberOfOneFourSevenAndEightOccurances = 0

    input.forEachLine { inputSignal ->
        val outputValues = inputSignal.split(" | ").get(1).split(" ")
        outputValues.forEach{
            if(it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7){
                numberOfOneFourSevenAndEightOccurances++
            }
        }
    }

    println("Number of 1, 4, 7 and 8 occurances: $numberOfOneFourSevenAndEightOccurances")

    // Part 2
    var totalOutput = 0

    input.forEachLine { inputSignal ->
        val inputValues = inputSignal.split(" | ").get(0).split(" ")
        val outputValues = inputSignal.split(" | ").get(1).split(" ")

        var one = ""
        var four = ""
        var seven = ""
        var eight = ""
        val stringOfFives = mutableListOf<String>()
        val stringOfSixes = mutableListOf<String>()

        inputValues.forEach{
            when(it.length){
                2 -> one = it
                4 -> four = it
                3 -> seven = it
                7 -> eight = it
                5 -> stringOfFives.add(it)
                6 -> stringOfSixes.add(it)
            }
        }

        var zero = ""
        var two = ""
        var three = ""
        var five = ""
        var six = ""
        var nine = ""

        stringOfSixes.forEach{
            if(!areCharsOfOneStringInAnother(one, it)){
                six = it
            }
        }
        stringOfSixes.remove(six)

        stringOfFives.forEach{
            if(areCharsOfOneStringInAnother(one, it)){
                three = it
            }
        }
        stringOfFives.remove(three)

        stringOfFives.forEach {
            val missingLetterInSix = eight.toSet() - six.toSet()
            if(it.toSet().containsAll(missingLetterInSix)){
                two = it
            } else {
                five = it
            }
        }

        stringOfSixes.forEach{
            if(areCharsOfOneStringInAnother(four, it)){
                nine = it
            } else {
                zero = it
            }
        }

        var currentOutput = ""
        outputValues.forEach {
            if(it.length == 2){
                currentOutput += "1"
            } else if(it.length == 4){
                currentOutput += "4"
            } else if(it.length == 3){
                currentOutput += "7"
            } else if(it.length == 7){
                currentOutput += "8"
            } else if(it.length == 5){
                if(areCharsOfOneStringInAnother(two, it)){
                    currentOutput += "2"
                } else if(areCharsOfOneStringInAnother(three, it)){
                    currentOutput += "3"
                } else if(areCharsOfOneStringInAnother(five, it)){
                    currentOutput += "5"
                }
            } else if(it.length == 6){
                if(areCharsOfOneStringInAnother(zero, it)){
                    currentOutput += "0"
                } else if(areCharsOfOneStringInAnother(six, it)){
                    currentOutput += "6"
                } else if(areCharsOfOneStringInAnother(nine, it)){
                    currentOutput += "9"
                }
            }
        }

        totalOutput += currentOutput.toInt()

    }

    println("Output part 2: $totalOutput")

}

fun areCharsOfOneStringInAnother(s1: String, s2: String) = s2.toSet().containsAll(s1.toSet())