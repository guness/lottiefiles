package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.guness.lottie.R
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.ui.theme.GoldenRatio
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.utils.extensions.toColor
import java.time.Instant

/**
 * Created by guness on 14.01.2022 14:06
 */

@Composable
fun HeroCard(animation: Animation?, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(GoldenRatio)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val background = animation?.bgColor?.toColor() ?: MaterialTheme.colors.onBackground
            Box(
                modifier = Modifier.background(background)
            ) {
                if (animation == null) {
                    Lottie(LottieCompositionSpec.RawRes(R.raw.shimmer))
                } else {
                    Lottie(LottieCompositionSpec.Url(animation.lottieUrl), contentScale = ContentScale.Fit)
                }
            }
        }
    }
}

@Composable
private fun Lottie(
    spec: LottieCompositionSpec,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val composition by rememberLottieComposition(spec)
    LottieAnimation(
        composition,
        modifier = modifier.fillMaxSize(),
        iterations = LottieConstants.IterateForever,
        contentScale = contentScale
    )
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    HeroCard(
        Animation(
            123, "Blog Title", "#FFFFFF", "lottieUrl", "gifUrl", "videoUrl", "imageUrl", Instant.now(), Animator(
                124, "Name", "avatarUrl"
            )
        )
    )
}
