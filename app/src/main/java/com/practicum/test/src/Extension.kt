package com.practicum.test.src

import kotlin.random.Random

fun main() {
    val boolean = Random.nextBoolean()
    println(boolean)
    println(boolean.toInt())
    val string = "Some text"
    string.printLn()
    val int = Random.nextInt(1, 10)
    println(int)
    println(int.percentOf(10))
    val string1 = "One more text"
    println(string1.divideBySeparator('_'))
}
fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}
// true.toInt() -> Результат '1'
// false.toInt() -> Результат '0'
fun String.printLn() {
    println(this)
}

// реализуйте функцию printLn() для класса String
// "Hello".printLn() -> Вывод в лог 'Hello'
fun Int.percentOf(integer: Int): Int {
    return (this / (integer / 100.0)).toInt()
}
// реализуйте функцию percentOf() для класса Int
// val value = 5
// value.percentOf(10) -> Результат '50'

fun String.divideBySeparator(separator: Char): String {
    val array = toCharArray()
    var result = ""
    for (character in array) {
        result += if (character == ' ') {
            separator
        } else {
            character
        }
    }
    return result
}