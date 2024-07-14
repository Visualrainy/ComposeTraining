package com.example.composetraining

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ProfileScreen() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(name = "Peng Chen Ins Official")
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection()
        Spacer(modifier = Modifier.height(20.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
        HighlightSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), highlights = listOf(
                StoryHighlight(icon = painterResource(id = R.drawable.youtube), "YouTube"),
                StoryHighlight(icon = painterResource(id = R.drawable.qa), "Q&A"),
                StoryHighlight(icon = painterResource(id = R.drawable.telegram), "Telegram"),
                StoryHighlight(icon = painterResource(id = R.drawable.discord), "Discord")
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(
            imageWithTexts = listOf(
                ImageWithText(icon = painterResource(id = R.drawable.ic_grid), "Posts"),
                ImageWithText(icon = painterResource(id = R.drawable.ic_reels), "Reels"),
                ImageWithText(icon = painterResource(id = R.drawable.ic_igtv), "Igtv"),
                ImageWithText(icon = painterResource(id = R.drawable.profile), "Profile")
            )
        ) {
            selectedIndex = it
        }

        when (selectedIndex) {
            0 -> PostSection(
                posts = listOf(
                    painterResource(id = R.drawable.starbucks),
                    painterResource(id = R.drawable.starbucks),
                    painterResource(id = R.drawable.starbucks),
                    painterResource(id = R.drawable.starbucks),
                    painterResource(id = R.drawable.starbucks),
                    painterResource(id = R.drawable.starbucks)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TopBar(
    name: String, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            contentDescription = "Notification",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            contentDescription = "menu",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun ProfileSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            RoundImage(
                painterResource(id = R.drawable.starbucks),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StateSection(modifier = Modifier.weight(7f))
        }
        ProfileDescription(
            name = "Programming mentor",
            description = "10 years of coding experience \n" + "Want me to make your app? Send me an email! \n" + "Subscribe to my YouTube channel!",
            url = "https://github.com/Visualrainy",
            followedNames = listOf("codingflow", "miakhalifa"),
            otherCount = 10
        )
    }
}

@Composable
fun RoundImage(
    image: Painter, modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(border = BorderStroke(1.dp, Color.LightGray), shape = CircleShape)
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StateSection(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        ProfileState("100", "Posts")
        ProfileState("100K", "Followers")
        ProfileState("80", "Following")
    }
}

@Composable
fun ProfileState(
    number: String, desc: String
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number, fontWeight = FontWeight.Bold, fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = desc, fontWeight = FontWeight.Bold, fontSize = 16.sp
        )
    }
}

@Composable
fun ProfileDescription(
    name: String, description: String, url: String, followedNames: List<String>, otherCount: Int
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            text = name, letterSpacing = letterSpacing, lineHeight = lineHeight
        )

        Text(
            text = description, letterSpacing = letterSpacing, lineHeight = lineHeight
        )

        Text(
            text = url,
            color = Color(0xff3d3dff),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        Text(text = buildAnnotatedString {
            val boldStyle = SpanStyle(
                color = Color.Black, fontWeight = FontWeight.Bold
            )
            append("Followed by ")
            followedNames.forEachIndexed { index, name ->
                pushStyle(boldStyle)
                append(name)
                pop()

                if (index < followedNames.size - 1) {
                    append(",")
                }
            }
            if (otherCount > 2) {
                append(" and ")
                pushStyle(boldStyle)
                append("$otherCount others")
            }
        })
    }
}

@Composable
fun ButtonSection(modifier: Modifier = Modifier) {
    val minWidth = 95.dp
    val height = 30.dp
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Message", modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            text = "Email", modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown, modifier = Modifier.height(height)
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier, text: String? = null, icon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ) {
        text?.takeIf { text.isNotBlank() }?.let {
            Text(
                text = it, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 14.sp
            )
        }
        icon?.let {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun HighlightSection(
    modifier: Modifier = Modifier, highlights: List<StoryHighlight>
) {
    LazyRow(modifier = modifier) {
        items(highlights.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                RoundImage(
                    image = highlights[it].icon,
                    modifier = Modifier.size(70.dp),
                )
                Text(
                    text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val inactiveColor = Color(0xff777777)
    TabRow(
        selectedTabIndex = selectedIndex,
        contentColor = Color.Black,
        modifier = modifier.background(Color.Transparent)
    ) {
        imageWithTexts.forEachIndexed { index, imageWithText ->
            Tab(selected = selectedIndex == index, onClick = {
                selectedIndex = index
                onTabSelected(index)
            }) {
                Icon(
                    painter = imageWithText.icon,
                    contentDescription = imageWithText.text,
                    tint = if (selectedIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(width = 1.dp, color = Color.White)
            )
        }
    }

}
