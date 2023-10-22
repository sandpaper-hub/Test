class PiggyBank {

    val moneyList: ArrayList<Money> = ArrayList() // список монеток/купюр
    var isBroken: Boolean = false // свойство, определяющее, разбита ли копилка

    fun putMoney(money: Money) {
        if (!isBroken) { // проверьте, не разбита ли копилка
            moneyList.add(money)
            println("Добавлено в копилку $money")// добавьте монетку в копилку
        } else println("Вы разбили копилку, вы больше ничего положить туда не можете")
    }

    fun shake(): Money? {
        return if (isBroken) { // проверьте, не разбита ли копилка
            println("Вы разбили копилку, больше оттуда ничего не вытрясти")
            null
        } else {
            val coinList = ArrayList<Money>()
            for (money in moneyList) {
                if (money.isCoin) {
                    coinList.add(money)
                }
            }
            if (coinList.isEmpty()) {
                return null
            } else {
                val coin: Money = coinList.random()
                moneyList.remove(coin)
                return coin
            }// вытрясти монетку из копилки (если в копилке нет монетки, вернуть null). Помните, купюры вытрясти нельзя.
        }
    }

    fun smash(): ArrayList<Money> {
        isBroken = true
        println("Копилка разбита, вы достали оттуда: $moneyList")
        return moneyList// установить флаг, что копилка разбита true, и вернуть все монетки, которые были в копилке
    }
}
