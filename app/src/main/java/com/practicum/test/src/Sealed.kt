package com.practicum.test.src

sealed class NetworkError(val message: String) {// реализуйте sealed класс com.practicum.test.src.NetworkError, который будет принимать на вход поле message типа String
// у класса com.practicum.test.src.NetworkError должно быть 3 наследника
    class ServerError(requestId: String, message: String?) : NetworkError("Ошибка взаимодействия с сервером для запроса: id = $requestId. Сообщение об ошибке: $message")  // ServerError -> принимает на вход 2 параметра: requestId: String и message: String?. Должен передавать в родительский класс com.practicum.test.src.NetworkError сообщение: "Ошибка взаимодействия с сервером для запроса: id = $requestId. Сообщение об ошибке: $message"
    class NoData(requestId: String) : NetworkError("Для запроса: id = $requestId нет данных")// NoData -> на вход принимает только requestId: String. Передаёт сообщение в конструктор родителя: "Для запроса: id = $requestId нет данных"
    class NoInternet(val requestId: String) : NetworkError("Нет подключения к интернету.") // NoInternet -> на вход принимает только requestId: String. Поле должно быть доступно за пределами класса, т. е. должно быть объявлено как val
                                                                                                  // Передаёт сообщение в конструктор родителя: "Нет подключения к интернету."
}

class ErrorHandler {

    fun handleError(error: NetworkError) {
        // в этот метод будут приходить ошибки, полученные при выполнении запросов
        // обработайте ошибки:
        when (error) {
            is NetworkError.ServerError -> println(error.message)// если это ошибка сервера (ServerError) - просто покажите сообщение с ошибкой
            is NetworkError.NoData -> showEmptyContent()// если данные не получены (NoData) - покажите пустой экран
            is NetworkError.NoInternet -> {// при отсутствии интернета (NoInternet) - показать ошибку пользователю и заново выполнить запрос (метод reloadRequest())
                showErrorMessage(error.message)
                reloadRequest(error.requestId)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        println(message)
    }

    private fun showEmptyContent() {
        println("Показываем пустой экран")
    }

    private fun reloadRequest(requestId: String) {
        println("При появлении подключения к интернету перезапускаем запрос: id = $requestId")
    }
}

class Network {

    fun onNetworkError(code: Int?, requestId: String, error: String?): NetworkError { // метод будет вызываться программой всякий раз, когда будет получена ошибка
        return when (code) {
            null -> NetworkError.NoInternet(requestId) // возвращать ошибку NoInternet, если code = null
            200 -> NetworkError.NoData(requestId) // возвращать ошибку NoData, если code = 200
            else -> NetworkError.ServerError(
                requestId,
                error
            ) // возвращать ошибку ServerError во всех остальных случаях
        }
    }
}
