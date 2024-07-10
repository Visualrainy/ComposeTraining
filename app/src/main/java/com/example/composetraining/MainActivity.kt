package com.example.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetraining.ui.HomeScreen
import com.example.composetraining.ui.theme.MeditationUIYouTubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUIYouTubeTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeditationUIYouTubeTheme {
        ContentView()
    }
}