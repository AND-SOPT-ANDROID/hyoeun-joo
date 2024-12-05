package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomHeader(
    startIcon: @Composable (() -> Unit)? = null,
    centerContent: @Composable () -> Unit,
    endIcon: @Composable (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Color(0xFF1B1B1B))
    ) {
        startIcon?.let { icon ->
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 16.dp)
            ) {
                icon()
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            centerContent()
        }
        endIcon?.let { icon ->
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(vertical = 16.dp)
            ) {
                icon()
            }
        }
    }
}
