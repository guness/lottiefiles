package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.ui.theme.Footnote1
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.Radius

private val WIDTH = 129.dp

@Composable
fun AnimatorsRow(animators: List<Animator>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Padding.s),
        horizontalArrangement = Arrangement.spacedBy(Padding.s)
    ) {
        if (animators.isEmpty()) {
            items(5) {
                AnimatorPlaceholder()
            }
        } else {
            items(animators) {
                AnimatorView(it)
            }
        }
    }
}

@Composable
fun AnimatorPlaceholder() {
    @Composable
    fun Line(width: Dp = WIDTH) = Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.onBackground)
            .size(width = width, height = 10.dp)
    )

    Column {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Radius.l))
                .background(MaterialTheme.colors.onBackground)
                .size(WIDTH)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Line()
        Spacer(modifier = Modifier.height(8.dp))
        Line(width = 90.dp)
    }
}

@Composable
fun AnimatorView(animator: Animator) {
    Column(
        modifier = Modifier.width(WIDTH)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Radius.l))
                .background(MaterialTheme.colors.onBackground)
                .size(WIDTH)
        ) {
            Image(
                painter = rememberImagePainter(animator.avatarUrl),
                contentDescription = null,
                modifier = Modifier.size(WIDTH),
                contentScale = ContentScale.Crop
            )
        }
        Box(modifier = Modifier.padding(top = Padding.xs, end = Padding.m)) {
            Footnote1(text = animator.name, maxLines = 2)
        }
    }
}

@Preview
@Composable
private fun PlaceholderPreview() = LottieTheme {
    AnimatorPlaceholder()
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
