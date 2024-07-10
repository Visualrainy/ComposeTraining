package com.example.composetraining.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetraining.R
import com.example.composetraining.standardQuadFromTo
import com.example.composetraining.ui.theme.AquaBlue
import com.example.composetraining.ui.theme.Beige1
import com.example.composetraining.ui.theme.Beige2
import com.example.composetraining.ui.theme.Beige3
import com.example.composetraining.ui.theme.BlueViolet1
import com.example.composetraining.ui.theme.BlueViolet2
import com.example.composetraining.ui.theme.BlueViolet3
import com.example.composetraining.ui.theme.ButtonBlue
import com.example.composetraining.ui.theme.DarkerButtonBlue
import com.example.composetraining.ui.theme.DeepBlue
import com.example.composetraining.ui.theme.LightGreen1
import com.example.composetraining.ui.theme.LightGreen2
import com.example.composetraining.ui.theme.LightGreen3
import com.example.composetraining.ui.theme.LightRed
import com.example.composetraining.ui.theme.OrangeYellow1
import com.example.composetraining.ui.theme.OrangeYellow2
import com.example.composetraining.ui.theme.OrangeYellow3
import com.example.composetraining.ui.theme.TextWhite

@Composable
@Preview
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
            CurrentMeditation()
            FeatureSection(
                features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ), Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ), Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ), Feature(
                        title = "Calming sounds", R.drawable.ic_headphone, Beige1, Beige2, Beige3
                    )
                )
            )
        }
        BottomMenus(
            menus = listOf(
                BottomMenu("Home", R.drawable.ic_home),
                BottomMenu("Meditate", R.drawable.ic_bubble),
                BottomMenu("Sleep", R.drawable.ic_moon),
                BottomMenu("Music", R.drawable.ic_music),
                BottomMenu("Profile", R.drawable.ic_profile),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenus(
    menus: List<BottomMenu>,
    modifier: Modifier = Modifier,
    activeHighLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedIndex: Int = 0
) {
    var selectedIndex by remember {
        mutableIntStateOf(initialSelectedIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        menus.forEachIndexed { index, bottomMenu ->
            BottomMenuItem(
                menu = bottomMenu,
                isSelected = selectedIndex == index,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor,
                activeHighLightColor = activeHighLightColor
            ) {
                selectedIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    menu: BottomMenu,
    isSelected: Boolean = false,
    activeHighLightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighLightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = menu.iconId),
                contentDescription = menu.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = menu.title, color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }

}

@Composable
fun GreetingSection(
    name: String = "Peng"
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name", style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "We wish you have a good day!", style = MaterialTheme.typography.bodyLarge
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var chipSelectedIndex by remember {
        mutableIntStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp)
                    .clickable {
                        chipSelectedIndex = it
                    }
                    .clip(MaterialTheme.shapes.large)
                    .background(
                        if (chipSelectedIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = LightRed
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .clip(MaterialTheme.shapes.large)
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought", style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Meditation * 3 - 10 min",
                style = MaterialTheme.typography.bodyLarge,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )

        }
    }
}

@Composable
fun FeatureSection(
    features: List<Feature>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(
    feature: Feature
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //medium color path
        val mediumColorPoint1 = Offset(0f, height * 0.3f)
        val mediumColorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
            standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
            standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
            standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        //light color path
        val lightColorPoint1 = Offset(0f, height * 0.3f)
        val lightColorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val lightColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val lightColorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val lightColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val lightColoredPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standardQuadFromTo(lightColorPoint1, lightColorPoint2)
            standardQuadFromTo(lightColorPoint2, lightColorPoint3)
            standardQuadFromTo(lightColorPoint3, lightColorPoint4)
            standardQuadFromTo(lightColorPoint4, lightColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath, color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath, color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(text = "Start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp))
        }
    }
}