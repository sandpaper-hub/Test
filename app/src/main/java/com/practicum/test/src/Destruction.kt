package com.practicum.test.src

class Point(private val x: Int, private val y: Int) {

    // перегрузите оператор plus
    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

    // перегрузите оператор minus
    operator fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y)
    }

    // перегрузите оператор times
    operator fun times(point: Point): Point {
        return Point(x * point.x, y * point.y)
    }

    // перегрузите оператор div
    operator fun div(point: Point): Point {
        return Point(x / point.x, y / point.y)
    }

    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}

fun main() {
    val point1 = Point(5, 8)
    val point2 = Point(3, 2)
    println(point1 + point2)
    println(point1 - point2)
    println(point1 * point2)
    println(point1 / point2)
}