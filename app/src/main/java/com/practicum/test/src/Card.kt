package com.practicum.test.src

import java.util.Scanner
import kotlin.random.Random

class Game {
    companion object {
        val input = Scanner(System.`in`)
    }
    fun start() {
        // поздоровайтесь с пользователем - выведите в лог "Привет, поиграем в лото?"
        makeLogMessage(LogMessage.HELLO)
        // создайте объект Lotto, для запуска
        val lotto = Lotto()
        // спросите у пользователя имя нового игрока "Введите имя нового игрока" и добавьте его в объект Lotto. Используйте input.nextLine() для считывания с консоли.
        createPlayer(lotto)
        // после добавление спросите у пользователя, хочет ли он добавить ещё одного пользователя "Если хотите добавить ещё игрока - введите любой символ, если хотите начать игру введите 'Нет'". Используйте input.nextLine() для считывания с консоли.
        createMorePlayer(lotto)
        // если пользователь введёт "Нет" - завершить добавление пользователей и запустить игру Lotto.start(), иначе добавить ещё одного пользователя. Добавление пользователей может быть бесконечным, если пользователь никогда не введёт "Нет"
    }

    private fun makeLogMessage(logMessage: LogMessage) {
        when (logMessage) {
            LogMessage.HELLO -> println(LogMessage.HELLO)
            LogMessage.INPUT_NEW_PLAYER -> println(LogMessage.INPUT_NEW_PLAYER)
            LogMessage.INPUT_MORE_PLAYER -> println(LogMessage.INPUT_MORE_PLAYER)
        }

    }

    private fun createPlayer(lotto: Lotto) {
        makeLogMessage(LogMessage.INPUT_NEW_PLAYER)
        val name = input.nextLine()
        lotto.addPerson(Person((name)))
    }

    private fun createMorePlayer(lotto: Lotto) {
        var isContinueAdding = true
        while (isContinueAdding) {
            makeLogMessage(LogMessage.INPUT_MORE_PLAYER)
            val inputResult = input.nextLine()
            if (inputResult.lowercase() == "нет") {
                isContinueAdding = false
                lotto.start()
            } else {
                createPlayer(lotto)
            }
        }
    }
}

enum class LogMessage(private val message: String) {
    HELLO("Привет, поиграем в лото?"),
    INPUT_NEW_PLAYER("Введите имя нового игрока"),
    INPUT_MORE_PLAYER("Если хотите добавить ещё игрока - введите любой символ, если хотите начать игру введите 'Нет'");


    override fun toString(): String {
        return message
    }
}

class Lotto {
    val playerList = ArrayList<Person>() // определите поле, в котором будут храниться добавленные игроки `Person`
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
    val game = Game()
    game.start()
}






