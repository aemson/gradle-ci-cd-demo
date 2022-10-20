package com.service

data class CalculatorInputs(val num1: Int, val num2: Int, val operation: String) {
    constructor() : this(0, 0, "plus")
}

fun calculate(num1: Int, num2: Int, function: (Int, Int) -> Int): String = "Output is ${function(num1, num2)}"

val add: (Int, Int) -> Int = { x, y -> x + y }
val sub: (Int, Int) -> Int = { x, y -> x - y }
