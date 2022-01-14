package com.guness.lottie.ui.activities.main.home

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
import com.guness.lottie.ui.theme.GoldenRatio
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.utils.extensions.toColor

/**
 * Created by guness on 14.01.2022 14:06
 */

@Composable
fun HeroCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(GoldenRatio)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.brightness))
        LottieAnimation(
            composition,
            modifier = Modifier
                .fillMaxSize()
                .background("#22292F".toColor()),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    HeroCard()
}
