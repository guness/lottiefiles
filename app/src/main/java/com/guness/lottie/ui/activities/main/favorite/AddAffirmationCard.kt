package com.guness.lottie.ui.activities.main.favorite


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guness.lottie.R
import com.guness.lottie.ui.theme.Body1
import com.guness.lottie.ui.theme.MiniButton
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.utils.OnClick

@Composable
fun AddAffirmationCard(modifier: Modifier = Modifier, onAddAffirmationClick: OnClick = {}) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier.clickable(onClick = onAddAffirmationClick)
        ) {
            Row(
                modifier = Modifier
                    .height(148.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(Padding.m),
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = stringResource(R.string.favorite_add_own_affirmation),
                    tint = MaterialTheme.colors.primaryVariant,
                )
                Spacer(modifier = Modifier.width(Padding.xs))
                Body1(
                    text = stringResource(R.string.favorite_add_own_affirmation)
                )
            }
        }
        MiniButton(
            onClick = {},
            modifier = Modifier.align(Alignment.BottomEnd),
            id = R.drawable.ic_lock
        )
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    AddAffirmationCard()
}
