package com.practicum.test.src

import kotlin.random.Random

fun main() {
    val catDownloaderV2 = CatDownloaderV2()

    // перепишите данную реализацию согласно требованиям в задаче
    val downloadedCats = catDownloaderV2.downloadCats(CATS_COUNT,
        onNext = {cat -> showCat(cat = cat) },
        onError = { showError() },
        onComplete = { showComplete() })
    //В новой реализации в выводе такого сообщения необходимости не будет
}


fun showCat(cat: Cat1) {
    println("Котик ${cat.name} успешно загружен")
}

fun showComplete() {
    println("Загрузка котиков завершена")
}

fun showError() {
    println("Упс. При загрузке котика произошла ошибка :(")
}

// константа для выбора количества загруженных котиков
const val CATS_COUNT = 5



class CatDownloaderV2 {

    // перепишите метод согласно заданию. Используйте функции в качестве параметров для реагирования на события загрузки котиков
    fun downloadCats(count: Int,
                     onNext: (cat: Cat1) -> Unit,
                     onComplete: () -> Unit,
                     onError: () -> Unit): List<Cat1?> {
        val catsList = mutableListOf<Cat1?>()
        for (i in 1..count) {
            val cat: Cat1? = getCatFromInternet()
            if (cat != null) {
                catsList.add(cat)
                onNext.invoke(cat)
            } else {
                onError()
            }
        }
        onComplete()

        return catsList
    }

    // этот метод переписывать не нужно. Но если вам хочется добавить разнообразия — вы можете придумать свою логику генерации котиков ¯\_(ツ)_/¯
    private fun getCatFromInternet(): Cat1? {
        return when (Random.nextInt(5)) {
            0 -> null
            1 -> Cat1("Борис")
            2 -> Cat1("Кузьма")
            3 -> Cat1("Барсик")
            4 -> Cat1("Кефирчик")
            else -> null
        }
    }
}

data class Cat(val name: String)