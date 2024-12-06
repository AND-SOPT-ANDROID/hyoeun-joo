package org.sopt.and.core.component.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun CustomPwTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    textColor: Color = Color.White,
    placeholderColor: Color = Color(0xFFA5A5A5),
    focusedBorderColor: Color = Color(0xFF2F2F2F),
    unfocusedBorderColor: Color = Color(0xFF2F2F2F),
    errorBorderColor: Color = Color.Red
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, if (isError) errorBorderColor else focusedBorderColor),
                        shape = RoundedCornerShape(10.dp)
                    ),
                placeholder = {
                    Text(placeholder, color = placeholderColor, fontSize = 14.sp)
                },
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = focusedBorderColor,
                    unfocusedContainerColor = unfocusedBorderColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor
                ),
                maxLines = 1
            )

            Text(
                text = if (passwordVisible) "hide" else "show",
                color = Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { passwordVisible = !passwordVisible }
            )
        }

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = errorBorderColor,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}
