package com.example.composetraining

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val icon: ImageVector,
    val text: String,
    val route: String,
    val badgeCount: Int = 0
)