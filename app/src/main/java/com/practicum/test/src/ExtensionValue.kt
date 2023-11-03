package com.practicum.test.src

class Person1(val name: String, val surname: String, val thirdName: String)

val String.firstSymbol: Char
    get() {
        val array = this.toCharArray()
        return array[0]
    }
// реализуйте свойство firstSymbol для класса String
// "Белка".firstSymbol -> Результат 'Б'
// "автомобиль".firstSymbol -> Результат 'а'

val Int.firstDigit: Int
    get() {
        val array = this.toString().toCharArray()
        return array[0].digitToInt()
    }
// реализуйте свойство firstDigit для класса Int
// val a = 435
// a.firstDigit -> Результат 4

val Person1.fio: String
    get() = "${this.surname} ${this.name} ${this.thirdName}"
// реализуйте свойство fio для класса Person
// Person("Андрей", "Стрельцов", "Александрович") -> Результат "Стрельцов Андрей Александрович"

val Int.biggestDigit: Int
    get() {
        val array = this.toString().toCharArray()
        var maxDigit = 0
        for (char in array) {
            val digit = char.digitToInt()
            if (digit > maxDigit) {
                maxDigit = digit
            }
        }
        return maxDigit
    }
// реализуйте свойство biggestDigit для класса Int
// val a = 435
// a.biggestDigit -> Результат 5

fun main() {
    val string = "Hello"
    println(string.firstSymbol)
    val digit = 234234
    println(digit.firstDigit)
    val person = Person1("Constantine", "Kim", "Yurevich")
    println(person.fio)
    println(digit.biggestDigit)
}