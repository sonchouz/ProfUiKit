package com.example.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class InputState {
    Normal,
    Error
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorText: String? = null,

) {
    val lineColor = Color(0xFFFF6480)


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "",
            color = UiKitTheme.colors.black,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            cursorBrush = SolidColor(UiKitTheme.colors.black),
            textStyle = TextStyle(
                color = UiKitTheme.colors.black,
                fontSize = 14.sp,
                lineHeight = 14.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ) {
                        if (value.isEmpty() && !placeholder.isNullOrBlank()) {
                            Text(
                                text = placeholder,
                                color = UiKitTheme.colors.black,
                                fontSize = 14.sp,
                                lineHeight = 14.sp
                            )
                        }
                        innerTextField()
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(lineColor)
                    )
                }
            }
        )

        if (isError && !errorText.isNullOrBlank()) {
            Text(
                text = errorText,
                color = UiKitTheme.colors.error,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun InputFieldPreview() {
    var state by remember { mutableStateOf(InputState.Normal) }
    var enabled by remember { mutableStateOf(true) }
    var errorText by remember { mutableStateOf<String?>(null) }
    var placeholder by remember { mutableStateOf("Full name") }
    var text by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        enabled = true
        placeholder = "Full name"
        errorText = null

        when (state) {
            InputState.Normal -> {
                placeholder = "Full name"
                errorText = null
            }

            InputState.Error -> {
                placeholder = "Full name"
                errorText = "Введите имя"
            }
        }
    }

    MyUiKitTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .background(UiKitTheme.colors.white)
                .padding(16.dp)
        ) {
            InputField(
                value = text,
                onValueChange = { text = it },
                placeholder = placeholder,
                enabled = enabled,
                isError = state == InputState.Error,
                errorText = errorText,

            )
        }
    }
}