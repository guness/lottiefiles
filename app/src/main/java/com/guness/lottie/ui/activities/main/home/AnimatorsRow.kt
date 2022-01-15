package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.ui.theme.Body1
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Padding

private val SIZE = 129.dp

@Composable
fun AnimatorsRow(animators: List<Animator>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Padding.s),
        horizontalArrangement = Arrangement.spacedBy(Padding.s)
    ) {
        items(animators) {
            AnimatorView(it)
        }
    }
}

@Composable
fun AnimatorView(animator: Animator) {
    Column(
        modifier = Modifier.width(SIZE)
    ) {
        Image(
            painter = rememberImagePainter(animator.avatarUrl),
            contentDescription = null,
            modifier = Modifier
                .size(SIZE)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape),
            contentScale = ContentScale.Crop
        )
        Body1(
            text = animator.name,
            modifier = Modifier
                .padding(start = Padding.s, top = Padding.xs, end = Padding.s)
                .width(SIZE),
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimatorPreview() = LottieTheme {
    AnimatorView(Animator(name = "Name One", avatarUrl = "image1"))
}

@Preview(showBackground = true)
@Composable
private fun RowPreview() = LottieTheme {
    AnimatorsRow(
        listOf(
            Animator(name = "Name One", avatarUrl = "image1"),
            Animator(name = "Name Two", avatarUrl = "image2"),
            Animator(name = "Name Three", avatarUrl = "image3")
        )
    )
}
