package com.guness.lottie.ui.activities.main.recent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.extensions.toColor
import java.time.Instant

/**
 * Created by guness on 8.11.2021 11:59
 */

@Composable
fun RecentCard(modifier: Modifier = Modifier, animation: Animation, onCategoryClick: OnClick = {}) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(GoldenRatio)

    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.Url(animation.lottieUrl))
        LottieAnimation(
            composition,
            modifier = Modifier
                .fillMaxSize()
                .background(animation.bgColor.toColor()),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(widthDp = 164)
@Composable
private fun CardPreview() = LottieTheme {
    RecentCard(animation = mockAnimation)
}

private val mockAnimation = Animation(
    id = 0,
    name = "Name One",
    bgColor = "#FFBBEE",
    lottieUrl = "lottieUrl",
    gifUrl = null,
    videoUrl = null,
    imageUrl = "imageUrl",
    createdAt = Instant.now(),
    createdBy = Animator(id = 0, name = "name", avatarUrl = "avatarUrl")
)