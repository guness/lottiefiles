package com.guness.lottie.ui.activities.main.home


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.R
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick

@Composable
fun DailyCard(modifier: Modifier = Modifier, onStartClick: OnClick = {}, onSettingsClick: OnClick = {}) {
    Box(modifier = modifier) {
        Card {
            Column {
                Title1(
                    text = stringResource(R.string.home_daily_practice),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.m, top = Padding.m, end = Padding.m)
                )
                Body2(
                    text = stringResource(R.string.home_quick_start),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.m, top = Padding.xs, end = Padding.m)
                )

                BigButton(
                    onClick = onStartClick,
                    modifier = Modifier.padding(all = Padding.m),
                    text = stringResource(R.string.start),
                    id = R.drawable.ic_play
                )
            }
        }
        IconButton(
            onClick = onSettingsClick, modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                modifier = Modifier.size(ButtonDefaults.IconSize),
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = stringResource(R.string.preferences),
                tint = MaterialTheme.colors.primaryVariant,
            )
        }
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    DailyCard()
}