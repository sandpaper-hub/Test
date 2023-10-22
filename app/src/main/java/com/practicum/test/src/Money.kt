class Money private constructor(val amount: Float, val isCoin: Boolean){

    companion object{
        val coins_10 = Money(0.1F, true)
        val coins_50 = Money(0.5F, true)
        val coins_100 = Money(1F, true)
        val bill_50 = Money(50F, false)
        val bill_100 = Money(100F, false)
        val bill_500 = Money(500F, false)
        val bill_1000 = Money(1000F, false)
    }

    override fun toString(): String {
        return if (amount in 0.1F..0.5F) "${(amount * 100).toInt()}коп." else "${amount.toInt()}руб."
    }
//... // создайте класс Money, который будет отражать сущность монетки/купюры с двумя полями: amount и isCoin
//... // переопределите метод toString() так, чтобы он возвращал строку типа "10 коп." или "1 руб.", если это монетка, и "100 руб.", если это купюра
//... // вы должны ограничить создание класса таким образом, чтобы можно было создать только ограниченный набор номиналов (см. задание)
}