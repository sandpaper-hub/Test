package com.practicum.test.src.arrays

class ArraySample {
    val odd: Array<Boolean> = Array(5) { index -> index % 2 > 0 }
    val integers: Array<Int> = Array(10) { index -> index * index}
    val someString: String = "We will rock you"
    val charArray: Array<String?> = Array(17) { index -> someString.substring(0, index)}

    fun addElement() {
        println(odd.contentToString())
        println(integers.contentToString())
        println(charArray.contentToString())
    }

    fun varargSample(vararg int: Int) {
        for (variable in int) {
            print("$variable ")
        }
    }
}

fun main() {
    val sample: ArraySample = ArraySample()
    sample.addElement()
    sample.integers[0] = 10
    sample.varargSample(1,2,3,4,5,6,7,8,9,10)
}