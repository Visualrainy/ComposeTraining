package com.example.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTrainingTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable(
            "profile/{name}/{userId}/{timestamp}",
            arguments = listOf(navArgument("name") { type = NavType.StringType },
                navArgument("userId") { type = NavType.StringType },
                navArgument("timestamp") { type = NavType.LongType })
        ) {
            val name = it.arguments?.getString("name") ?: ""
            val userId = it.arguments?.getString("userId") ?: ""
            val timeStamp = it.arguments?.getLong("timestamp") ?: 0

            ProfileScreen(navController, name, userId, timeStamp)
        }
        composable(
            "post/{showOnlyPostsByUser}", arguments = listOf(navArgument("showOnlyPostsByUser") {
                type = NavType.BoolType
                defaultValue = false
            })
        ) {
            val showOnlyPostsByUser = it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
            PostScreen(showOnlyPostsByUser)
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Screen")
        Button(onClick = {
            navController.navigate("profile/philip/userid/123456789")
        }) {
            Text(text = "Go to Profile Screen")
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController, name: String, userId: String, created: Long
) {
    val user = remember {
        User(
            name, userId, LocalDateTime.ofInstant(
                Instant.ofEpochMilli(created), ZoneId.systemDefault()
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate("post/true")
        }) {
            Text(text = "Go to Post Screen")
        }
    }
}

@Composable
fun PostScreen(showOnlyPostsByUser: Boolean = false) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen: $showOnlyPostsByUser")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTrainingTheme {
        ContentView()
    }
}