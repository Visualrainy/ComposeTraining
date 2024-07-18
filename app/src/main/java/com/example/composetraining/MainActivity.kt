package com.example.composetraining

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetraining.ui.theme.BottomNavWithBadgesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavWithBadgesTheme {
                ContentView()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContentView() {
    val navigationController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(
            items = listOf(
                BottomNavItem(
                    text = "Home", route = "home", icon = Icons.Default.Home
                ),
                BottomNavItem(
                    text = "Chat",
                    route = "notification",
                    icon = Icons.Default.Notifications,
                    badgeCount = 23
                ),
                BottomNavItem(
                    text = "Settings",
                    route = "setting",
                    icon = Icons.Default.Settings,
                    badgeCount = 214
                ),
            ),
            navController = navigationController,
        ) {
            navigationController.navigate(route = it.route)
        }
    }) {
        Navigation(navigationController = navigationController)
    }
}

@Composable
fun Navigation(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Screen.HomeScreen.name) {
        composable(route = Screen.HomeScreen.name) {
            HomeScreenView()
        }
        composable(route = Screen.NotificationScreen.name) {
            NotificationScreenView()
        }

        composable(route = Screen.SettingScreen.name) {
            SettingScreenView()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onClick: (BottomNavItem) -> Unit
) {

    NavigationBar(
        modifier = modifier.background(Color.DarkGray)
    ) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        items.forEachIndexed { _, item ->
            val selected = backStackEntry.value?.destination?.route == item.route
            NavigationBarItem(selected = selected, colors = NavigationBarItemDefaults.colors().copy(
                selectedIconColor = Color.Green,
                selectedTextColor = Color.Green,
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            ), onClick = {
                onClick(item)
            }, icon = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BadgedBox(badge = {
                        if (item.badgeCount > 0) {
                            Badge {
                                Text(text = item.badgeCount.toString())
                            }
                        }
                    }) {
                        Icon(imageVector = item.icon, contentDescription = item.text)
                    }
                    if (selected) {
                        Text(text = item.text, textAlign = TextAlign.Center, fontSize = 10.sp)
                    }
                }
            })
        }
    }
}

@Composable
fun HomeScreenView() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Home", textAlign = TextAlign.Center, fontSize = 10.sp)
    }
}

@Composable
fun NotificationScreenView() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Notification")
    }
}

@Composable
fun SettingScreenView() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Settings")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomNavWithBadgesTheme {
        ContentView()
    }
}