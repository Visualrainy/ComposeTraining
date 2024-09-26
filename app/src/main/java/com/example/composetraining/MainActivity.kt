package com.example.composetraining

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var p1 = remember { "a" }
            ContentView(p1) {
                p1 = if (p1 == "a") {
                    println("B")
                    "b"
                } else {
                    println("A")
                    "a"
                }
            }
        }
    }
}

@Composable
fun ContentView(p1: String, clickBack: () -> Unit) {
    var fibonacci by remember { mutableStateOf("1") }
    val fibonacciVM = viewModel<FibonacciViewModel>()
    var count by remember { mutableStateOf("1") }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fibonacci, modifier = Modifier.padding(16.dp),
            fontSize = 28.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = count,
                onValueChange = {
                    count = it
                },
            )
            if (p1 == "a") {
                println("p1 == a")
            }
            Button(modifier = Modifier.padding(8.dp),
                onClick = {
                    if (count.isNotBlank()) {
                        fibonacci = fibonacciVM.calculate(count.toInt()).toString()
                    }
                }) {
                Text(
                    text = "start",
                    textAlign = TextAlign.Center,
                )
            }
        }
        
        // New "Run" button
        var runButtonText by remember { mutableStateOf("Run") }
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                runButtonText = "Begin"
                clickBack()
            }
        ) {
            Text(
                text = runButtonText,
                textAlign = TextAlign.Center,
            )
        }
        
        // New list showing numbers 1 to 10
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(List(10) { it + 1 }) { number ->
                Text(
                    text = number.toString(),
                    modifier = Modifier.padding(vertical = 4.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTrainingTheme {
        var p1 = remember { "a" }
        ContentView(p1) {
            p1 = if (p1 == "a") "b" else "a"
            println("preview")
            Log.d("xxxx", "preview $p1")
        }
    }
}