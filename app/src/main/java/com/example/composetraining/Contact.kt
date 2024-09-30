package com.example.composetraining

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Contact(
    val name: String,
    val number: String
)

data class Snack(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: List<String> = emptyList()
)

data class SnackMutable(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
) {
    var tagline by mutableStateOf("")
}

data class SnackMutable2(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
) {
    val tagline by mutableStateOf("")
}
@Stable
data class SnackStable(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: List<String> = mutableStateListOf()
)

@Stable
data class SnackStable2(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    var tags: List<String> = mutableStateListOf()
)

@Immutable
data class SnackImmutable(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: List<String> = emptyList()
)