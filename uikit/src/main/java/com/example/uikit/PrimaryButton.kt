package com.example.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UiKitPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            androidx.compose.ui.graphics.Color(0xFFFF5F7E),
            androidx.compose.ui.graphics.Color(0xFFFF3D7A)
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                brush = gradient,
                shape = RoundedCornerShape(100.dp) // pill
            )
            .clickable(enabled = enabled) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = UiKitTheme.colors.white,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun UiKitPrimaryButtonPreview() {
    MyUiKitTheme {
        UiKitPrimaryButton(
            text = "Let’s Combat!",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
