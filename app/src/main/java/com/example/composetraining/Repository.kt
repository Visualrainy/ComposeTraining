package com.example.composetraining

import kotlinx.coroutines.delay

class Repository {
    private val remoteDataSource = (1..100).map {
        Item(
            title = "Item $it",
            description = "Description $it"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<Item>> {
        delay(2000)
        val startIndex = page * pageSize
        return if (startIndex + pageSize <= remoteDataSource.size) {
            Result.success(remoteDataSource.slice(startIndex until startIndex + pageSize))
        } else {
            Result.success(emptyList())
        }
    }
}