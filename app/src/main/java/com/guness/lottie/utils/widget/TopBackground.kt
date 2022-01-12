package com.guness.lottie.utils.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.ui.theme.Radius
import com.guness.lottie.ui.theme.lottieGradient

private val shape = RoundedCornerShape(bottomStart = Radius.l, bottomEnd = Radius.l)

@Composable
fun TopBackground() = Box(
    modifier = Modifier
        .clip(shape)
        .background(
            brush = lottieGradient(
                rotation = -70,
                offset = Offset(1.3f, 1.09f)
            )
        )
        .fillMaxWidth()
        .fillMaxHeight(0.44f)
)

@Preview(widthDp = 300, heightDp = 600)
@Composable
private fun Preview() {
    Box {
        TopBackground()
    }
}
