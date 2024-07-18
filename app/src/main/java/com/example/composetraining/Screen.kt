package com.example.composetraining

sealed class Screen(val name: String) {
    data object HomeScreen : Screen("home")
    data object NotificationScreen : Screen("notification")
    data object SettingScreen : Screen("setting")
}