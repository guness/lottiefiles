package com.guness.lottie.ui.activities.main.home


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guness.lottie.R
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick

@Composable
fun PremiumCard(modifier: Modifier = Modifier, onSubscribeClick: OnClick = {}) {

    val border = BorderStroke(
        4.dp, lottieGradient(
            rotation = -70,
            offset = Offset(1.3f, 1.09f)
        )
    )
    Card(
        modifier = modifier, border = border
    ) {
        Column(
            modifier = Modifier.padding(Padding.m),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title2(
                text = stringResource(R.string.home_subscribe_premium),
                modifier = Modifier.padding(start = Padding.m, end = Padding.m, bottom = Padding.xs)
            )

            stringArrayResource(id = R.array.home_premium_checks).forEach {
                FeatureLine(text = it, modifier = Modifier.padding(top = Padding.s))
            }

            BigButton(
                onClick = onSubscribeClick,
                modifier = Modifier.padding(top = Padding.m),
                text = stringResource(R.string.subscribe)
            )
        }
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    PremiumCard()
}