package com.practicum.test.src

import kotlin.random.Random


fun main() {
    val catDownloader = CatDownloader()

    catDownloader.downloadCat(
        onSuccess = { cat -> println("Кот ${cat.name} загружен успешно") },
        onError = { error -> println(error) },
        onStart = { println("Загрузчик Котиков начинает работу") },
        false
    )
}

class CatDownloader {

    // передаём в аргументы две функции onSuccess и onError
    fun downloadCat(
        onSuccess: (Cat1) -> Unit,
        onError: (String) -> Unit,
        onStart: () -> Unit,
        allowErrors: Boolean
    ) {
        onStart.invoke()
        while (!allowErrors) {
            val cat: Cat1? = getCatFromInternet()
            if (cat != null) {
                onSuccess.invoke(cat)
                break
            } else {
                onError.invoke("Упс, что-то пошло не так при загрузке котеек.")
            }
        }
    }

    private fun getCatFromInternet(): Cat1? {
        return if (Random.nextBoolean()) Cat1("Борис") else null
    }
}

class Cat1(val name: String)