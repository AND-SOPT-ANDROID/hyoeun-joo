package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DividerWithText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            color = Color.Gray,
            thickness = 1.dp
        )
        Text(
            text = text,
            modifier = Modifier
                .background(color = Color(0xFF1B1B1B))
                .align(Alignment.Center)
                .padding(horizontal = 8.dp),
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}
