package 여행경로

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/43164
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-N%EC%9C%BC%EB%A1%9C-%ED%91%9C%ED%98%84
*/
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

    for(i in tickets.indices) {
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
    }
}