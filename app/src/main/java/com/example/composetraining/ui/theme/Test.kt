package com.example.composetraining.ui.theme

// 0 1 1 2 3 5 8 13 21 34 55 ...

interface FibonacciProtocol {
    fun fibonacci(n: Int): Int
}

class RecursionFibonacci: FibonacciProtocol {
    private val cachedResult = mutableMapOf<Int, Int>()
    override fun fibonacci(n: Int): Int {
        val cachedValue = cachedResult.get(n)
        if (cachedValue != null) return cachedValue

        if(n == 0 || n == 1) return n

        val result = fibonacci(n - 1) + fibonacci(n-2)
        cachedResult.put(n, result)
        return result
    }
}

class ForLoopFibonacci: FibonacciProtocol {

    override fun fibonacci(n: Int): Int {
        var lastResult: Int = 0
        var result = 0

        (0..n).forEach{
            if(it == 0 || it == 1) {
                lastResult = result
                result = it
            } else {
                var temp = result
                result += lastResult
                lastResult = temp
            }
        }
        return result
    }
}

fun main() {
    // n: 0, 1
    val recursionFabonacci = RecursionFibonacci()
    var result = -1
    (0..1).forEach {
        result = recursionFabonacci.fibonacci(2)
        // assertEquals(it, result)
    }

    result = recursionFabonacci.fibonacci(8)
    // assertEquals(13, result)
}