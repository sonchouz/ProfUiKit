package com.example.uikit


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UiKitCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(
        modifier = modifier
            .size(16.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(2.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFFF4F7D),
                shape = RoundedCornerShape(2.dp)
            )
            .clickable(enabled = enabled) {
                onCheckedChange(!checked)
            },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Image(
                painter = painterResource(R.drawable.checkmark),
                contentDescription = null,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun UiKitCheckboxPreview() {
    val checkedState = androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(true)
    }

    MyUiKitTheme {
        UiKitCheckbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
    }
}