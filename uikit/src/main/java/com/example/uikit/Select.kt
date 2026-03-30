package com.example.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UiKitDropdown(
    value: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier,
    placeholder: String = "MON, NOV 4, 2019",
    enabled: Boolean = true
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(enabled = enabled) {
                    showBottomSheet = true
                },
            label = {
                Text(text = placeholder)
            },
            placeholder = {
                Text(placeholder)
            },
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.dropdown),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedLabelColor = Color(0xFFFA5075),
                unfocusedLabelColor = Color(0xFFFA5075),
                focusedIndicatorColor = Color(0xFFFA5075),
                unfocusedIndicatorColor = Color(0xFFFA5075)
            )
        )

        if (showBottomSheet) {
            ScheduleBottomSheet(
                selectedValue = value,
                options = options,
                onDismiss = {
                    showBottomSheet = false
                },
                onOptionClick = { selectedItem ->
                    onValueChange(selectedItem)
                    showBottomSheet = false
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun UiKitDropdownPreview() {
    var selectedDate by rememberSaveable { mutableStateOf("") }

    MyUiKitTheme {
        UiKitDropdown(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            options = listOf(
                "MON, NOV 4, 2019",
                "TUE, NOV 5, 2019",
                "WED, NOV 6, 2019",
                "THU, NOV 7, 2019",
                "FRI, NOV 8, 2019"
            ),
            placeholder = "MON, NOV 4, 2019",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}