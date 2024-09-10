@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.composetraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.ui.theme.ComposeTrainingTheme
import kotlinx.coroutines.launch

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
    val sheetScaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = sheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Bottom sheet", fontSize = 60.sp)
            }
        },
        sheetContainerColor = Color.Green,
        sheetPeekHeight = 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                scope.launch {
                    if (sheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                        sheetScaffoldState.bottomSheetState.partialExpand()
                    } else {
                        sheetScaffoldState.bottomSheetState.expand()
                    }
                }
            }) {
                Text(text = "Toggle sheet fraction: ${sheetScaffoldState.bottomSheetState.requireOffset()}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTrainingTheme {
        ContentView()
    }
}