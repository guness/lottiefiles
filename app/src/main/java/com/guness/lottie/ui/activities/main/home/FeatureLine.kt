package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.R
import com.guness.lottie.ui.theme.Body2
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.LottieTheme

/**
 * Created by guness on 17.11.2021 10:48
 */
@Composable
fun FeatureLine(text: String, modifier: Modifier = Modifier) = Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
) {
    Image(painter = painterResource(id = R.drawable.ic_wavy_check), contentDescription = null)
    Body2(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Padding.xs, end = Padding.m)
    )
}

@Preview(widthDp = 300)
@Composable
private fun LinePreview() = LottieTheme {
    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        FeatureLine("Practice affirmations with nature sound.")
    }
}