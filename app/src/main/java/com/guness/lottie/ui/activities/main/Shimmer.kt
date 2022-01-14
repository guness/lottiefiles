package com.guness.lottie.ui.activities.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.guness.lottie.R

/**
 * Created by guness on 14.01.2022 21:58
 */
@Composable
fun Shimmer(modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colors.onBackground)) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.shimmer))
        LottieAnimation(
            composition,
            modifier = modifier.fillMaxSize(),
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.Crop
        )
    }
}
