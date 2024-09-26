package com.example.composetraining

import androidx.lifecycle.ViewModel

class FibonacciViewModel : ViewModel() {
    private val cacheMap = mutableMapOf<Int, Int>()

    fun calculate(count: Int): Int {
        if (cacheMap.containsKey(count)) return cacheMap[count] ?: 0
        if (count == 1 || count == 2) return count
        cacheMap[count] = calculate(count - 1) + calculate(count - 2)
        return cacheMap[count] ?: 0
    }
}

//fun main() {
//    val vm = FibonacciViewModel()
//    println(vm.calculate(10))
//}


// 0 1 1 2 3 5 8 13 21 34 55 ...

interface FibonacciProtocol {
    fun fibonacci(n: Int): Int
}

class RecursionFibonacci: FibonacciProtocol {


    override fun fibonacci(n: Int): Int {

        if(n == 0 || n == 1) return n

        return fibonacci(n - 1) + fibonacci(n - 2)
    }
}

class ForLoopFibonacci: FibonacciProtocol {

    override fun fibonacci(n: Int): Int {
        var lastResult: Int = 0
        var result = 0

        (0..n).forEach {
            if(it == 0 || it == 1) {
                lastResult = result
                result = it
            } else {
                val temp = result
                result += lastResult
                lastResult = temp
            }
        }
        return result
    }
}

fun main() {
    // n: 0, 1
    val recursionFibonacci = RecursionFibonacci()
    var result = -1
    (0..1).forEach {
        result = recursionFibonacci.fibonacci(2)
        println("input $it, result: $result")
//        assertEquals(it, result)
    }

    result = recursionFibonacci.fibonacci(8)
    println("input 8, result: $result")
//    assertEquals(13, result)

    // n: 0, 1
    val forLoopFibonacci = ForLoopFibonacci()
    var forLoopFibonacciResult = -1
    (0..1).forEach {
        forLoopFibonacciResult = forLoopFibonacci.fibonacci(2)
        println("input $it, result: $forLoopFibonacciResult")
//        assertEquals(it, result)
    }

    forLoopFibonacciResult = forLoopFibonacci.fibonacci(3)
    println("input 10, result: $forLoopFibonacciResult")
//    assertEquals(13, result)
}
