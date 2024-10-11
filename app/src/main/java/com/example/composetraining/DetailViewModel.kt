package com.example.composetraining

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailViewModel(
    private val navigator: Navigator
) : ViewModel() {
    fun navigateUp() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }
}