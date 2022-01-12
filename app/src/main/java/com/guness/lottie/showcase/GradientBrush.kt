package com.guness.lottie.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guness.lottie.ui.theme.lottieGradient

@Preview(widthDp = 300, heightDp = 600)
@Composable
fun GradientBrush() {
    val widthDp = 375.dp
    val heightDp = 375.dp

    Box {
        Box(
            modifier = Modifier
                .background(
                    brush = lottieGradient(
                        rotation = 89,
                        offset = Offset(0.5f, 0.5f)
                    )
                )
                .size(width = widthDp, height = heightDp)
        )
    }
}
