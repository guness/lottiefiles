package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.MockAnimation
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.extensions.toColor
import com.guness.lottie.utils.widget.Shimmer

private val WIDTH = 220.dp

@Composable
fun FeaturedRow(
    animations: List<Animation>, modifier: Modifier = Modifier, onAnimationClick: OnClick<Long> = { }
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Padding.s),
        horizontalArrangement = Arrangement.spacedBy(Padding.s)
    ) {
        if (animations.isEmpty()) {
            items(5) {
                FeaturedPlaceholder()
            }
        } else {
            items(animations) {
                FeaturedView(it, onAnimationClick = onAnimationClick)
            }
        }
    }
}

@Composable
fun FeaturedPlaceholder(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(WIDTH)
            .aspectRatio(GoldenRatio)
    ) {
        Shimmer()
    }
}

@Composable
fun FeaturedView(animation: Animation, modifier: Modifier = Modifier, onAnimationClick: OnClick<Long> = {}) {
    Column(
        modifier = modifier
            .width(WIDTH)
            .clip(Shapes.medium)
            .background(MaterialTheme.colors.surface.copy(alpha = TransparentAlpha))
            .clickable { onAnimationClick(animation.id) }
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.Url(animation.lottieUrl))
        LottieAnimation(
            composition,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(GoldenRatio)
                .background(animation.bgColor.toColor())
                .clip(Shapes.medium),

            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Fit
        )
        Footnote1(
            text = animation.name,
            modifier = Modifier.padding(
                start = Padding.xs,
                top = Padding.xs,
                end = Padding.xs
            ),
            maxLines = 2
        )
        Caption1(
            text = animation.createdBy.name,
            modifier = Modifier.padding(
                start = Padding.xs,
                end = Padding.xs,
                bottom = Padding.xs
            ),
        )
    }
}

@Preview
@Composable
private fun PlaceholderPreview() = LottieTheme {
    FeaturedPlaceholder()
}

@Preview(showBackground = true)
@Composable
private fun AnimationPreview() = LottieTheme {
    FeaturedView(MockAnimation)
}

@Preview(showBackground = true)
@Composable
private fun RowPreview() = LottieTheme {
    FeaturedRow(listOf(MockAnimation, MockAnimation, MockAnimation))
}
