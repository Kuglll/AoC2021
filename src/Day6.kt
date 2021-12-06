import java.io.File

fun main() {

    val input = File("src/Inputs/Day6.txt").readLines()

    //Part 1

    var fishesTimers = input.get(0).split(",").toMutableList()
    var DAYS = 80
    var numberOfNewFishes = 0

    for (i in 0 until DAYS) {
        for (x in 0 until numberOfNewFishes) {
            fishesTimers.add("8")
        }

        numberOfNewFishes = 0
        for (i in 0 until fishesTimers.size) {
            if (fishesTimers.get(i).toInt() == 0) {
                fishesTimers[i] = "6"
                numberOfNewFishes++
            } else {
                fishesTimers[i] = "" + (fishesTimers[i].toInt() - 1)
            }
        }
    }

    println("Number of fishes after ${DAYS} days: ${fishesTimers.count() + numberOfNewFishes}")

    //Part 2
    DAYS = 256

    var fishesTimersMap = mutableMapOf<String, Long>()
    var numberOfFishes = input.get(0).split(",").count().toLong()

    input.get(0).split(",").forEach {
        if (!fishesTimersMap.contains(it)) {
            fishesTimersMap[it] = 1
        } else {
            fishesTimersMap[it] = fishesTimersMap[it]!! + 1
        }
    }

    var tempFishesTimersMap = mutableMapOf<String, Long>()
    for (i in 0 until DAYS) {

        if(tempFishesTimersMap.isNotEmpty()){
            fishesTimersMap = tempFishesTimersMap
            tempFishesTimersMap = mutableMapOf()
        }
        for ((key, value) in fishesTimersMap) {
            when (key) {
                "0" -> {
                    numberOfFishes += value
                    if("6" in tempFishesTimersMap){
                        tempFishesTimersMap["6"] = tempFishesTimersMap["6"]!! + value
                    } else{
                        tempFishesTimersMap["6"] = value
                    }
                    if("8" in tempFishesTimersMap){
                        tempFishesTimersMap["8"] = tempFishesTimersMap["8"]!! + value
                    } else{
                        tempFishesTimersMap["8"] = value
                    }
                }
                "1" ->{
                    tempFishesTimersMap["0"] = value
                }
                "2" ->{
                    tempFishesTimersMap["1"] = value
                }
                "3" ->{
                    tempFishesTimersMap["2"] = value
                }
                "4" ->{
                    tempFishesTimersMap["3"] = value
                }
                "5" ->{
                    tempFishesTimersMap["4"] = value
                }
                "6" ->{
                    tempFishesTimersMap["5"] = value
                }
                "7" ->{
                    if("6" in tempFishesTimersMap){
                        tempFishesTimersMap["6"] = tempFishesTimersMap["6"]!! + value
                    } else{
                        tempFishesTimersMap["6"] = value
                    }
                }
                "8" ->{
                    tempFishesTimersMap["7"] = value
                }
            }
        }
    }

    println("Number of fishes after ${DAYS} days: ${numberOfFishes}")
}