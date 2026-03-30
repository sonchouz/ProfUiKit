package com.example.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
data class BottomMenuItem(
    val title: String,
    val icon: Int
)
@Composable
fun UiKitBottomBar(
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
    onCenterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomMenuItem("Statistics", R.drawable.statistics_icon),
        BottomMenuItem("Discover", R.drawable.location_pin),
        BottomMenuItem("Chat", R.drawable.chat),
        BottomMenuItem("Profile", R.drawable.profile)
    )

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFF5C86),
                            Color(0xFFFF3F7D)
                        )
                    ),
                    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                )
                .padding(top = 14.dp, start = 18.dp, end = 18.dp, bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                BottomBarItem(
                    item = items[0],
                    selected = selectedIndex == 0,
                    onClick = { onItemClick(0) }
                )

                BottomBarItem(
                    item = items[1],
                    selected = selectedIndex == 1,
                    onClick = { onItemClick(1) }
                )

                Box(modifier = Modifier.size(64.dp))

                BottomBarItem(
                    item = items[2],
                    selected = selectedIndex == 2,
                    onClick = { onItemClick(2) }
                )

                BottomBarItem(
                    item = items[3],
                    selected = selectedIndex == 3,
                    onClick = { onItemClick(3) }
                )
            }
        }

        CenterBottomBarButton(
            onClick = onCenterClick,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )

        SpacerForNavigationBar()
    }
}

@Composable
private fun CenterBottomBarButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(76.dp)
            .shadow(
                elevation = 10.dp,
                shape = CircleShape,
                clip = false
            )
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFF5C86),
                            Color(0xFFFF3F7D)
                        )
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
               painter = painterResource(R.drawable.schedule),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    item: BottomMenuItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = item.title,
            modifier = Modifier.size(18.dp)
        )

        Text(
            text = item.title,
            color = Color.White,
            fontSize = 11.sp
        )
    }
}


@Composable
private fun SpacerForNavigationBar() {
    Box(
        modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)
    )
}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun UiKitBottomBarPreview() {
    var selectedIndex by remember { mutableIntStateOf(1) }

    MyUiKitTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.BottomCenter
        ) {
            UiKitBottomBar(
                selectedIndex = selectedIndex,
                onItemClick = { selectedIndex = it },
                onCenterClick = {}
            )
        }
    }
}