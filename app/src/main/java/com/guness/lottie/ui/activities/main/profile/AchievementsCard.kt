package com.guness.lottie.ui.activities.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guness.lottie.R
import com.guness.lottie.ui.theme.Body1
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Title2

/**
 * Created by guness on 17.11.2021 10:36
 */
@Composable
fun AchievementsCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier.defaultMinSize(minHeight = 180.dp)) {
        Column {
            Body1(
                text = stringResource(id = R.string.profile_achievements),
                modifier = Modifier.padding(Padding.m),
                color = MaterialTheme.colors.primary
            )

            Title2(
                text = stringResource(id = R.string.profile_no_achievements),
                modifier = Modifier
                    .padding(Padding.m)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(widthDp = 340)
@Composable
private fun CardPreview() = LottieTheme {
    AchievementsCard()
}
