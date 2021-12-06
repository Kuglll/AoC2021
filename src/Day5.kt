import java.io.File

fun main() {

    // Part 1
    val input = File("src/Inputs/Day5.txt")
    val map = mutableMapOf<Pair<Int, Int>, Int>()

    input.forEachLine {
        val (startingCoordinates, endingCoordinates) = it.split("->")
        val (x1String, y1String) = startingCoordinates.trim().split(",")
        val (x2String, y2String) = endingCoordinates.trim().split(",")

        val x1 = x1String.toInt()
        val x2 = x2String.toInt()
        val y1 = y1String.toInt()
        val y2 = y2String.toInt()

        if (x1 == x2) {
            if (y1 > y2) {
                fillMap(map, y2, y1, x1, true)
            } else {
                fillMap(map, y1, y2, x1, true)
            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                fillMap(map, x2, x1, y1)
            } else {
                fillMap(map, x1, x2, y1)
            }
        } else { //Part 2
            if(x1 < x2 && y1 < y2){
                //x up, y up
                for (i in 0 .. x2 - x1){
                    val coordinates = Pair(x1+i, y1+i)
                    if (coordinates in map) {
                        map[coordinates] = map.get(coordinates)!! + 1
                    } else {
                        map[coordinates] = 1
                    }
                }

            } else if(x1 > x2 && y1 < y2){
                //x down, y up
                for (i in 0 .. x1 - x2){
                    val coordinates = Pair(x1-i, y1+i)
                    if (coordinates in map) {
                        map[coordinates] = map.get(coordinates)!! + 1
                    } else {
                        map[coordinates] = 1
                    }
                }
            } else if(x1 < x2 && y1 > y2){
                //x up, y down
                for (i in 0 .. x2 - x1){
                    val coordinates = Pair(x1+i, y1-i)
                    if (coordinates in map) {
                        map[coordinates] = map.get(coordinates)!! + 1
                    } else {
                        map[coordinates] = 1
                    }
                }
            } else if(x1 > x2 && y1 > y2){
                //x down, y down
                for (i in 0 .. x1 - x2){
                    val coordinates = Pair(x1-i, y1-i)
                    if (coordinates in map) {
                        map[coordinates] = map.get(coordinates)!! + 1
                    } else {
                        map[coordinates] = 1
                    }
                }
            }
        }

    }

    println("How many points overlap at least 2 times: ${map.values.count { it >= 2 }}")

}

fun fillMap(
    map: MutableMap<Pair<Int, Int>, Int>,
    smallerIndex: Int,
    biggerIndex: Int,
    fixedCoordinate: Int,
    isXFixedCoordinate: Boolean = false
) {
    for (i in smallerIndex..biggerIndex) {
        val coordinates = if (isXFixedCoordinate) Pair(fixedCoordinate, i) else Pair(i, fixedCoordinate)
        if (coordinates in map) {
            map[coordinates] = map.get(coordinates)!! + 1
        } else {
            map[coordinates] = 1
        }
    }
}