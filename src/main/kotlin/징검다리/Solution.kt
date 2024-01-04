package 징검다리

// https://school.programmers.co.kr/learn/courses/30/lessons/43236?language=kotlin
fun solution(distance: Int, rocks: IntArray, bombCount: Int): Int {
    rocks.sort()
    return binarySearch(rocks, bombCount, distance, 1, distance)
}

fun binarySearch(rocks: IntArray, bombCount: Int, distance: Int, left: Int, right: Int): Int {
    val center = (left+right)/2
    if(left >= right) {
        if(explodeMoreThenN(rocks, bombCount, distance, left)) return left - 1
        else return left
    }
    return if(explodeMoreThenN(rocks, bombCount, distance, center)) {
        binarySearch(rocks, bombCount, distance, left, center)
    } else {
        binarySearch(rocks, bombCount, distance, center+1, right)
    }
}

fun explodeMoreThenN(rocks: IntArray, bombCount: Int, distance: Int, minDistance: Int): Boolean {
    var lastRockPosition = 0
    var explodeCount = 0

    rocks.forEach {
        if(it - lastRockPosition >= minDistance) lastRockPosition = it
        else explodeCount++
    }
    if(distance - lastRockPosition < minDistance) explodeCount++
    return explodeCount > bombCount
}

class InputCase {
    companion object {
        val distance =
            5
            //25
        val rocks =
            intArrayOf(1,4)
            //intArrayOf(2,11,14,17,21) // 2 9 3 3 4 4
        val n =
            1
            //2
    }
}