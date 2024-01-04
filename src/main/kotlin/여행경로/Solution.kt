package 여행경로

// https://school.programmers.co.kr/learn/courses/30/lessons/43164
fun solution(tickets: Array<Array<String>>): Array<String> {
    val map = mutableMapOf<String, ArrayList<String>>()
    val visitedMap = mutableMapOf<String, ArrayList<Boolean>>()
    tickets.forEach {
        if (map[it[0]] == null) {
            map[it[0]] = arrayListOf()
            visitedMap[it[0]] = arrayListOf()
        }
        map[it[0]]?.add(it[1])
        visitedMap[it[0]]?.add(false)
    }
    map.forEach {
        it.value.sort()
    }

    return dfs(map, visitedMap, "ICN", 0, tickets.size).toTypedArray().reversedArray()
}

fun dfs(map: Map<String, List<String>>, visitedMap: Map<String, ArrayList<Boolean>>, start: String, depth: Int, ticketSize: Int): ArrayList<String> {
    val tickets = map.getOrDefault(start, arrayListOf())
    val visitedList = visitedMap.getOrDefault(start, arrayListOf())

    if(depth == ticketSize) {
        return arrayListOf(start)
    }

    for(i in 0 until tickets.size) {
        if(!visitedList[i]) {
            visitedList[i] = true
            val result = dfs(map, visitedMap, tickets[i], depth + 1, ticketSize)
            if (result.size != 0) {
                result.add(start)
                return result
            }
            visitedList[i] = false
        }
    }
    return arrayListOf()
}

class InputCase {
    companion object {
        val tickets = arrayOf(
            arrayOf("ICN", "ATL"),
            arrayOf("ICN", "SFO"),
            arrayOf("SFO", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO"),
        )
    //    = arrayOf(
    //        arrayOf("ICN", "AAA"),
    //        arrayOf("AAA", "BBB"),
    //        arrayOf("BBB", "AAA"),
    //        arrayOf("AAA", "BBB"),
    //    )
    }
}