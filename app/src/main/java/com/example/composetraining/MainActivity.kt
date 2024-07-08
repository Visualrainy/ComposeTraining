package com.example.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContentView()
        }
    }
}

@Composable
fun ContentView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressBar(percentage = 0.8f, number = 100)
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animateDuration: Int = 1000,
    animateDelay: Int = 0
) {
    var animatePlayed by remember {
        mutableStateOf(false)
    }

    val animatePercentage by animateFloatAsState(
        targetValue = if (animatePlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animateDuration,
            delayMillis = animateDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animatePlayed = true
    }

    Box(
        modifier = Modifier
            .size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier
            .size(radius * 2f)) {
            drawArc(
                color = color,
                -90f,
                360 * animatePercentage,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (animatePercentage * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTrainingTheme {
        ContentView()
    }
}