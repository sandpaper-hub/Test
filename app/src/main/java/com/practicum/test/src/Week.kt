enum class Week(val localizedName: String) {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    fun isWeekDay(): String {
        return when (this) {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "будний"
            else -> "выходной"
        }
    }
}

fun main() {
    val days = Week.values()

    for (day in days) {
        println("${day.localizedName} - ${day.isWeekDay()} день")
    }
}