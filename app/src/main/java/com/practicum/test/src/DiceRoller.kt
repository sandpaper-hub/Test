import kotlin.random.Random

class DiceRollerV2 {

    // сделайте интерфейс OnRollCallback функциональным
    fun interface OnRollCallback {
        fun call(firstDiceValue: Int, secondDiceValue: Int)
    }

    // сделайте интерфейс OnDoubleCallback функциональным
    fun interface OnDoubleCallback {
        fun call(diceValue: Int)
    }

    private var onRollCallback: OnRollCallback? = null
    private var onDoubleCallback: OnDoubleCallback? = null

    fun setCallbacks(onRollCallback: OnRollCallback, onDoubleCallback: OnDoubleCallback) {
        this.onRollCallback = onRollCallback
        this.onDoubleCallback = onDoubleCallback
    }

    fun roll() {
        if (onRollCallback == null || onDoubleCallback == null) {
            println("Вы должны вызвать функцию setCallbacks() прежде чем бросать кубики")
            return
        }

        val firstDiceValue = Random.nextInt(1, 7)
        val secondDiceValue = Random.nextInt(1, 7)

        if (firstDiceValue != secondDiceValue) {
            onRollCallback?.call(firstDiceValue, secondDiceValue)
        } else {
            onDoubleCallback?.call(firstDiceValue)
        }
    }
}

fun main() {
    // ваш код здесь
    val diceRollerV2 = DiceRollerV2()
    val onRollCallback = DiceRollerV2.OnRollCallback {first, second -> println("На кубиках выпало $first и $second")}
    val onDoubleCallback = DiceRollerV2.OnDoubleCallback { diceValue ->
        println("Ура!!! Дубль на $diceValue-ах! Бросаем ещё раз")
        diceRollerV2.roll()
    }
    diceRollerV2.setCallbacks(onRollCallback, onDoubleCallback)

    for (i in 1..10) {
        diceRollerV2.roll()
    }
}