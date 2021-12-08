import java.io.File
import kotlin.math.abs

fun main(){

    val input = File("src/Inputs/Day7.txt").readLines()

    val crabPositions = input.get(0).split(",").map { it.toInt() }
    val maxPosition = crabPositions.maxOrNull()
    val minPosition = crabPositions.minOrNull()
    var leastFuelNeeded = crabPositions.sumOf { it }

    // Part 1
    for(i in minPosition!!.toInt() .. maxPosition!!.toInt()){
        var currentFuelNeeded = 0
        crabPositions.forEach { crabPosition ->
            currentFuelNeeded += abs(crabPosition - i)
            if(currentFuelNeeded > leastFuelNeeded){
                return@forEach
            }
        }
        if(currentFuelNeeded < leastFuelNeeded){
            leastFuelNeeded = currentFuelNeeded
        }
    }

    println("Least fuel needed: $leastFuelNeeded")

    // Part 2
    leastFuelNeeded = 1000000000

    for(i in minPosition!!.toInt() .. maxPosition!!.toInt()){
        var currentFuelNeeded = 0
        crabPositions.forEach { crabPosition ->
            for(x in 0 until abs(crabPosition-i)){
                currentFuelNeeded += x+1
            }
            if(currentFuelNeeded > leastFuelNeeded){
                return@forEach
            }
        }
        if(currentFuelNeeded < leastFuelNeeded){
            leastFuelNeeded = currentFuelNeeded
        }
    }

    println("Least fuel needed part 2: $leastFuelNeeded")


}