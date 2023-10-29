package com.practicum.test.src

import kotlin.random.Random

class Lotto {
    private val playerList = ArrayList<Person>() // определите поле, в котором будут храниться добавленные игроки `Person`
    private var haveWinner = false

    // поле thrownNumbers должно хранить в себе набор выброшенных чисел.
    val thrownNumbers: HashSet<Int> = hashSetOf() // определите подходящую структуру данных

    fun addPerson(person: Person) {
        // добавить игрока в список игроков
        playerList.add(person)
    }

    fun start() {
        if (playerList.size < 2) {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
            return
            // вывести сообщение "Перед началом игры необходимо добавить хотя бы двух игроков" и завершить работу, если количество добавленных игроков меньше 2
        }

        while (!haveWinner) {// выбрасывать новые числа до тех пор, пока не определится победитель
            val randomNumber = randomNumber() // достать номер. Номер может быть в диапазоне от 1 до 99 включительно
            for (player in playerList) {
                for (i in 1..3) {
                    val hashSet: MutableSet<Int> = player.card.numbers.getValue(i)
                    if (hashSet.contains(randomNumber)) {
                        hashSet.remove(randomNumber) // после каждого выброшенного числа удалять это число из карточек всех игроков, если такое число имеется
                    }
                    winnerCheck(player, hashSet)
                    // побеждает тот, у кого в одном из рядов нет больше чисел. Победителей может быть более одного
                }
            }
            gameOver()
        }


        // после того как появляется победитель, для каждого победителя вывести текст "Победитель: [имя_победителя]!!!"
    }

    private fun randomNumber(): Int {
        while (true) {
            val random = Random.nextInt(1, 100)
            if (!thrownNumbers.contains(random)) {
                thrownNumbers.add(random)
                println("Выброшенное число: $random")
                return random
            }
        }
    }

    private fun winnerCheck(player: Person, mutableSet: MutableSet<Int>) {
        if (mutableSet.isEmpty()) {
            player.isWinner = true
        }
    }

    private fun gameOver() {

        for (player in playerList) {
            if (player.isWinner) {
                println("Победитель: ${player.name}!!!")
                haveWinner = true
            }
        }
    }
}

class Card(val numbers: Map<Int, MutableSet<Int>>)

class Person(val name: String) {

    var isWinner  = false
    val card: Card = createCard()

    private fun createCard(): Card {
        val numbers: Set<Int> = generateNumbers()

        val iterator: Iterator<Int> = numbers.iterator()
        var currentLine = 1

        val cardNumbers: MutableMap<Int, MutableSet<Int>> = mutableMapOf(
            1 to mutableSetOf(),
            2 to mutableSetOf(),
            3 to mutableSetOf()
        )

        while (iterator.hasNext()) {
            val number = iterator.next()
            cardNumbers[currentLine]?.add(number)

            if (currentLine < 3) {
                currentLine++
            } else {
                currentLine = 1
            }
        }

        return Card(cardNumbers)
    }

    private fun generateNumbers(): Set<Int> {
        val numbers: MutableSet<Int> = mutableSetOf()

        while (numbers.size < NUMBERS_COUNT) {
            numbers.add(Random.nextInt(MIN_NUMBER, MAX_NUMBER))
        }

        return numbers
    }

    private companion object {

        private const val NUMBERS_COUNT = 15
        private const val MAX_NUMBER = 100
        private const val MIN_NUMBER = 1
    }
}

fun main() {
    val alex = Person("Alex")
    val sam = Person("Sam")
    val jim = Person("Jim")
    println(alex.card.numbers)
    println(sam.card.numbers)
    println(jim.card.numbers)
    val lotto = Lotto()
    lotto.addPerson(alex)
    lotto.addPerson(jim)
    lotto.addPerson(sam)

    lotto.start()
}






