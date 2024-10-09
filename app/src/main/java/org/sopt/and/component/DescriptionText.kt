package org.sopt.and.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun DescriptionText(text: String) {
    Text(
        text = text,
        color = Color(0xFFA5A5A5),
        fontSize = 12.sp
    )
}
