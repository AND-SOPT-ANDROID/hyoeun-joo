package org.sopt.and.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    passwordVisible: Boolean = true,
    padding: PaddingValues = PaddingValues(0.dp),
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder, color = Color(0xFFA5A5A5), fontSize = 14.sp) },
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), // 암호화 적용
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF2F2F2F),
            unfocusedContainerColor = Color(0xFF2F2F2F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
        ),
        maxLines = 1,
    )
}