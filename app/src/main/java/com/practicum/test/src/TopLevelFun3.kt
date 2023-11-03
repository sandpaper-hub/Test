package com.practicum.test.src

data class Query(val type: Int, val message: String) {
    companion object {
        const val ERROR = 0
        const val WARNING = 1
    }
}

class QueryHandler {

    fun handle(queriesList: List<Query>,
               onError: (String) -> Unit,
               onWarning: (String) -> Unit,
               onOther: (Int, String) -> Unit) {
        for (query in queriesList) {
            when(query.type) {
                Query.ERROR -> onError.invoke(query.message)
                Query.WARNING -> onWarning.invoke(query.message)
                else -> onOther.invoke(query.type, query.message)
            }
        }
    }

//    fun sendToRefactoring(message: String) {
//        println("Ошибка \"$message\" будет срочно исправлена")
//    }
//
//    fun sendToAuthors(message: String) {
//        println("Неточность \"$message\" будет исправлена в ближайшем обновлении")
//    }
//
//    fun sendToSupport(type : Int, message: String) {
//        println("Запрос \"$message\" с типом \"$type\" будет рассмотрен командой поддержки")
//    }
}