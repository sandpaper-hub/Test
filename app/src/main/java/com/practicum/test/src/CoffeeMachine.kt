package com.practicum.test.src

import java.util.*

class CoffeeMachine {
    private var water: Int = 0
    private var milk: Int = 0
    private var beans: Int =0
    private val scanner = Scanner(System.`in`)
    private var command = ""

    private fun makeCoffee(coffee: String) {
        when (coffee) {
            Coffee.ESPRESSO.toString() -> subtractIngredients(Coffee.ESPRESSO)
            Coffee.AMERICANO.toString() -> subtractIngredients(Coffee.AMERICANO)
            Coffee.CAPPUCCINO.toString() -> subtractIngredients(Coffee.CAPPUCCINO)
            Coffee.LATTE.toString() -> subtractIngredients(Coffee.LATTE)
            else -> println("Рецепт не найден!")
        }
    }

    private fun subtractIngredients(coffee: Coffee) {
        if (water >= coffee.water && milk >= coffee.milk && beans >= coffee.beans) {
            water -= coffee.water
            milk -= coffee.milk
            beans -= coffee.beans
            println("$coffee готов")
        } else {
            if (water < coffee.water) {
                println("Недостаточно воды!")
                return
            }
            if (milk < coffee.milk){
                println("Недостаточно молока!")
                return
            }
            if (beans < coffee.beans) {
                println("Недостаточно кофе!")
                return
            }
        }
    }

    public fun start() {
        println("Кофемашина готова к работе")
        while (true) {
            println("Введите команду")
            if (scanner.hasNextLine()) {
                command = scanner.nextLine().lowercase()
                when (command) {
                    "выключить" -> {
                        println("До свидания!")
                        break
                    }

                    "наполнить" -> {
                        water = 2000
                        milk = 1000
                        beans = 500
                        println("Ингридиенты пополнены")
                    }

                    "статус" -> {
                        println("Ингридиентов осталось:")
                        println("$water мл воды\n$milk мл молока\n$beans гр кофе")
                    }

                    "кофе" -> {
                            println("Введите название напитка или \"назад\" для возврата в главное меню")
                            if (scanner.hasNextLine()) {
                                val coffee = scanner.nextLine().lowercase()
                                if (coffee == "назад") {
                                    continue
                                } else {
                                    makeCoffee(coffee)
                                }
                            }
                    }
                    else -> println("Неизвестная команда")
                }
            }
        }
    }
}

enum class Coffee(private val coffeeType: String, val water: Int, val milk: Int, val beans: Int) {
    ESPRESSO("эспрессо", 60, 0, 10),
    AMERICANO("американо", 120, 0, 10),
    CAPPUCCINO("капучино", 120, 60, 20),
    LATTE("латте", 240, 120, 20);

    override fun toString(): String {
        return coffeeType
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.start()
}