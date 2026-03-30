package com.example.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleBottomSheet(
    selectedValue: String,
    options: List<String>,
    onDismiss: () -> Unit,
    onOptionClick: (String) -> Unit,
    title: String = "Schedule",
    description: String = "Easily schedule event/games then find like minded players for battle. You up for it?"
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .width(36.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(UiKitTheme.colors.description)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SchedulePromoCard(
                title = title,
                description = description
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Choose date",
                color = UiKitTheme.colors.white,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(options) { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickableWithoutRipple {
                                scope.launch {
                                    onOptionClick(item)
                                    sheetState.hide()
                                    onDismiss()
                                }
                            }
                            .padding(vertical = 14.dp)
                    ) {
                        Text(
                            text = item,
                            color = if (item == selectedValue) {
                                UiKitTheme.colors.error
                            } else {
                                UiKitTheme.colors.white
                            },
                            fontSize = 14.sp,
                            lineHeight = 16.sp
                        )
                    }

                    HorizontalDivider(
                        color = UiKitTheme.colors.card_stroke
                    )
                }
            }
        }
    }
}

@Composable
private fun SchedulePromoCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFF5C7A),
                        androidx.compose.ui.graphics.Color(0xFFFF3F7D)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    color = UiKitTheme.colors.white,
                    fontSize = 20.sp,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = description,
                    color = UiKitTheme.colors.white,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Image(
                    painter = painterResource(R.drawable.forward_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            PromoIllustrationPlaceholder()
        }
    }
}

@Composable
private fun PromoIllustrationPlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 108.dp, height = 112.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(10.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(50))
                .background(androidx.compose.ui.graphics.Color(0x33FFFFFF))
        )

        Box(
            modifier = Modifier
                .size(58.dp)
                .align(Alignment.BottomStart)
                .clip(CircleShape)
                .background(androidx.compose.ui.graphics.Color(0xFFEFEAFB))
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(18.dp)
                    .align(Alignment.Center)
                    .background(androidx.compose.ui.graphics.Color(0xFF6A56D9))
            )
            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(3.dp)
                    .align(Alignment.Center)
                    .background(androidx.compose.ui.graphics.Color(0xFF6A56D9))
            )

            Box(
                modifier = Modifier
                    .size(6.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
                    .background(androidx.compose.ui.graphics.Color(0xFF6A56D9))
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 4.dp, end = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(androidx.compose.ui.graphics.Color(0xFFFFD54F))
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 18.dp)
                .size(26.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(androidx.compose.ui.graphics.Color(0xFFFFD54F))
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(androidx.compose.ui.graphics.Color(0xFF2D2D3A))
            )

            Spacer(modifier = Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(androidx.compose.ui.graphics.Color(0xFFFFD54F))
            )

            Spacer(modifier = Modifier.height(2.dp))

            Row {
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(androidx.compose.ui.graphics.Color(0xFF2D4D9F))
                )

                Spacer(modifier = Modifier.width(4.dp))

                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(androidx.compose.ui.graphics.Color(0xFF2D4D9F))
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(UiKitTheme.colors.white)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(UiKitTheme.colors.white)
                )
            }
        }
    }
}

@Composable
fun Modifier.clickableWithoutRipple(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = this.clickable(
    enabled = enabled,
    indication = null,
    interactionSource = remember { MutableInteractionSource() }
) {
    onClick()
}

@Preview(showBackground = true)
@Composable
private fun ScheduleBottomSheetContentPreview() {
    var selectedValue by rememberSaveable { mutableStateOf("MON, NOV 4, 2019") }

    MyUiKitTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SchedulePromoCard(
                title = "Schedule",
                description = "Easily schedule event/games then find like minded players for battle. You up for it?"
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}