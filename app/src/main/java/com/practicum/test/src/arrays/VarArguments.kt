package com.practicum.test.src.arrays

import kotlin.math.abs

fun average(vararg ints: Int): Double {
    var sum = 0
    for (value: Int in ints) {
        sum += value
    }
    return sum.toDouble() / ints.size
}
// реализуйте функцию average()
// average(1, 4, 4) -> Результат 3

fun longestWord(vararg strings: String): String {
    var stringSize = 0
    var longestString = ""
    for (string: String in strings) {
        if (string.length > stringSize) {
            stringSize = string.length
            longestString = string
        }
    }
    return longestString
}
// реализуйте функцию longestWord()
// longestWord("Я", "люблю", "гулять") -> Результат "гулять"


fun nearestNumber(parameter: Int, vararg ints: Int): Int {
    var minDifference = abs(parameter - ints[0])
    var nearestValue = 0
    for (i in ints.indices) {
        val difference = abs(parameter - ints[i])
        if (minDifference >= difference) {
            minDifference = difference
            nearestValue = ints[i]
        }
    }
    return nearestValue
}

// реализуйте функцию nearestNumber()
// nearestNumber(10, 4, 12, 9) -> Результат 9
fun main() {
    val arrayOfInt = intArrayOf(-15, 5, 4, 3, 2, 1, -5, -12)
    val arrayOfString = arrayOf("1", "22", "333")
    println(average(*arrayOfInt))
    println(longestWord(*arrayOfString))
    println(nearestNumber(4, *arrayOfInt))
}
