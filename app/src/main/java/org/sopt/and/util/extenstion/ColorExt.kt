package org.sopt.and.util.extenstion

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


fun String.applyColorSpan(
    start: Int,
    end: Int,
    color: Color,
    textSize: TextUnit = 22.sp,
    defaultColor: Color = Color.Gray,
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(style = SpanStyle(color = defaultColor, fontSize = textSize)) {
            append(this@applyColorSpan.substring(0, start))
        }

        withStyle(style = SpanStyle(color = color, fontSize = textSize)) {
            append(this@applyColorSpan.substring(start, end))
        }

        withStyle(style = SpanStyle(color = defaultColor, fontSize = textSize)) {
            append(this@applyColorSpan.substring(end))
        }
    }
}
