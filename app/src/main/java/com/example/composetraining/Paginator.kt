package com.example.composetraining

interface Paginator<K, T> {
    suspend fun loadNextItems()
    fun reset()
}