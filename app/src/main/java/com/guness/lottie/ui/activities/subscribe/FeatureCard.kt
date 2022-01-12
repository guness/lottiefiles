package com.guness.lottie.ui.activities.subscribe

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.ui.activities.main.home.FeatureLine
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.LottieTheme

/**
 * Created by guness on 17.11.2021 10:47
 */
@Composable
fun FeatureCard(modifier: Modifier = Modifier, text: String) {
    Card(modifier = modifier) {
        FeatureLine(text = text, modifier = Modifier.padding(Padding.m))
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    FeatureCard(text = "It does not matter how slowly you go as long as you do not stop.")
}
