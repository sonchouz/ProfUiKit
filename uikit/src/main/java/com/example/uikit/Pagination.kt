package com.example.uikit


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UiKitPagination(
    total: Int,
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(total) { index ->
            val isActive = index == currentIndex

            val color = if (isActive) {
                UiKitTheme.colors.error
            } else {
                UiKitTheme.colors.placeholder
            }

            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .size(if (isActive) 8.dp else 6.dp)
                    .background(
                        color = color,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PaginationPreview() {
    MyUiKitTheme {
        UiKitPagination(
            total = 3,
            currentIndex = 0,
            modifier = Modifier.padding(16.dp)
        )
    }
}