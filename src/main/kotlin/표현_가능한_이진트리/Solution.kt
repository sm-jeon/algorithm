package 표현_가능한_이진트리

import kotlin.math.log

// https://school.programmers.co.kr/learn/courses/30/lessons/150367
fun solution(numbers: LongArray): IntArray {
    val numbersBits = numbers.map {
        (log(it.toDouble(), 2.0) + 1).toInt()
    }.map {
        var i = 1
        while(i < it) {
            i *= 2
        }
        i*2-1
    }

    val binaryArray = numbers.zip(numbersBits) { number, bits ->
        val binaryNumber = number.toString(2)
        val array = Array(bits-binaryNumber.length) {0}.toMutableList()
        array.addAll(binaryNumber.toCharArray().map { it.digitToInt() })
        array
    }
    val result = binaryArray.map {
        var startIdx = 0
        var endIdx = it.size-1
        var centerIdx = endIdx/2
        var res = false
        if(it.size==1) res = true
        while(startIdx < endIdx) {
            if(checkBinaryTree(it.subList(startIdx, endIdx+1))) res = true
            if(it.subList(startIdx, centerIdx).contains(1)) break
            startIdx = centerIdx+1
            centerIdx = (endIdx + startIdx) / 2
        }
        res
    }.map { if (it) 1 else 0 }
    return result.toIntArray()
}

fun checkBinaryTree(list: List<Int>): Boolean {
    val center = list.size/2
    val left = getLeft(center, list.size - center)
    val right = getRight(center, list.size - center)

    if(left >= right) return true
    if(!checkBinaryTree(list.subList(0, center)) || !checkBinaryTree(list.subList(center+1, list.size))) return false;

    if((list[center]==0 && list[left]==1) || (list[center]==0 && list[right]==1)) {
        return false
    } else {
        return true
    }
}

fun getLeft(current: Int, length: Int) = current - length/2

fun getRight(current: Int, length: Int) = current + length/2

class InputCase {
    companion object {
        val numbers =
            //longArrayOf(16)
            //longArrayOf(1,2,4,8,16,32,64,128,256,512,1024)
            //(0..1000).map { it.toLong() }.toLongArray()
            longArrayOf(1,7,42,5,63,111,95)
    }
}