package com.example.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.destinations.PostScreenDestination
import com.example.composetraining.destinations.ProfileScreenDestination
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime

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
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@Destination(start = true)
@Composable
fun LoginScreen(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Screen")
        Button(onClick = {
            navigator.navigate(
                ProfileScreenDestination(
                    User("Chris", "userId", LocalDateTime.now())
                )
            )
        }) {
            Text(text = "Go to Profile Screen")
        }
    }
}

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator, user: User
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navigator.navigate(PostScreenDestination())
        }) {
            Text(text = "Go to Post Screen")
        }
    }
}

@Destination
@Composable
fun PostScreen(showOnlyPostsByUser: Boolean = false) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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