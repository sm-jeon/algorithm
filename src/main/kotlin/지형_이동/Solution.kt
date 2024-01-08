package 지형_이동

import kotlin.math.abs
import kotlin.math.min

//https://school.programmers.co.kr/learn/courses/30/lessons/62050
fun solution(landHeightMap: Array<IntArray>, height: Int): Int {
    val landGroupMap = Array(landHeightMap.size) { Array(landHeightMap.size){0}.toIntArray() }
    val edgeMap = mutableMapOf<Pair<Int, Int>, Int>()
    var groupCount = 0
    for(y in landHeightMap.indices) {
        for(x in landHeightMap.indices) {
            if(landGroupMap[y][x]==0) {
                groupCount++
                makeGroupByDfs(landHeightMap, landGroupMap, edgeMap, Position(x, y, landHeightMap.size-1), groupCount, height, -1)
            }
        }
    }
    val sortedEdgeList = edgeMap.map {
        Edge(it.key.first, it.key.second, it.value)
    }.sortedBy { it.cost }

    return kruskal(sortedEdgeList, groupCount)
}

class Edge(
    val from: Int,
    val to: Int,
    val cost: Int
)

class Position(
    val x: Int,
    val y: Int,
    val maxX: Int,
    val maxY: Int = maxX
) {
    fun up() = if(y==0) null else Position(x, y-1, maxX, maxY)
    fun down() = if(y==maxY) null else Position(x, y+1, maxX, maxY)
    fun left() = if(x==0) null else Position(x-1, y, maxX, maxY)
    fun right() = if(x==maxX) null else Position(x+1, y, maxX, maxY)
}

fun Array<IntArray>.findByPosition(position: Position) = this[position.y][position.x]

fun makeGroupByDfs(landHeightMap: Array<IntArray>, landGroupMap: Array<IntArray>, edgeMap: MutableMap<Pair<Int,Int>,Int>, position: Position?, groupName: Int, maxHeight: Int, befHeight: Int) {
    if(position == null) return
    val currentGroupName = landGroupMap.findByPosition(position)
    val currentHeight = landHeightMap.findByPosition(position)
    if(currentGroupName!=0) {
        if(currentGroupName > groupName) {
            edgeMap[groupName to currentGroupName] = min(abs(befHeight - currentHeight), edgeMap[groupName to currentGroupName] ?: Int.MAX_VALUE)
        } else if(currentGroupName < groupName) {
            edgeMap[currentGroupName to groupName] = min(abs(befHeight - currentHeight), edgeMap[currentGroupName to groupName] ?: Int.MAX_VALUE)
        }
        return
    }
    if(abs(befHeight - currentHeight) > maxHeight && befHeight != -1) return // height차이로 갈수없는곳

    landGroupMap[position.y][position.x] = groupName

    makeGroupByDfs(landHeightMap, landGroupMap, edgeMap, position.up(), groupName, maxHeight, currentHeight)
    makeGroupByDfs(landHeightMap, landGroupMap, edgeMap, position.left(), groupName, maxHeight, currentHeight)
    makeGroupByDfs(landHeightMap, landGroupMap, edgeMap, position.down(), groupName, maxHeight, currentHeight)
    makeGroupByDfs(landHeightMap, landGroupMap, edgeMap, position.right(), groupName, maxHeight, currentHeight)
}

fun kruskal(edgeList: List<Edge>, groupCount: Int): Int {
    val union = IntArray(groupCount+1) {it}
    var result = 0
    edgeList.forEach {
        if(!findParent(union, it.from, it.to)){
            result += it.cost
            unionParent(union, it.from, it.to)
        }
    }
    return result
}

fun unionParent(union: IntArray, a: Int, b: Int) {
    val aParent = getParent(union, a)
    val bParent = getParent(union, b)
    if(aParent < bParent) union[bParent] = aParent
    else union[aParent] = bParent
}

fun findParent(union: IntArray, a: Int, b: Int): Boolean {
    return getParent(union, a) == getParent(union, b)
}

fun getParent(union: IntArray, a: Int): Int {
    val parent = union[a]
    if(parent == a) return parent
    val root = getParent(union, parent)
    union[a] = root
    return root
}

class InputCase {
    companion object {
        val land =
            arrayOf(
                intArrayOf(1, 4, 8, 10),
                intArrayOf(5, 5, 5, 5) ,
                intArrayOf(10, 10, 10, 10) ,
                intArrayOf(10, 10, 10, 20),
                )
//            arrayOf(
//                intArrayOf(10, 11, 10, 11),
//                intArrayOf(2, 21, 20, 10),
//                intArrayOf(1, 20, 21, 11),
//                intArrayOf(2, 1, 2, 1),
//            )
        val height = 3
    }
}