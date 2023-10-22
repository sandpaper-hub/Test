fun main() {
    val a = 5
    val b: String = when (a) {
        in 1..6 -> "да, a  в диапазоне от 1 до 6"
        in 6..10 -> "нет"
        else -> "неизвестный диапазон"
    }
}