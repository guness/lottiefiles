package com.guness.lottie.ui.activities.main.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.guness.lottie.R
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick

@Composable
fun QuoteCard(modifier: Modifier = Modifier, quote: String, author: String, onShareClick: OnClick = {}) {
    Box(modifier = modifier) {
        Card {
            Column {
                Body1(
                    text = stringResource(R.string.home_quote_of_day),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.m, top = Padding.m, end = Padding.m)
                )
                Title2(
                    text = quote,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.m, top = Padding.m, end = Padding.m)
                )
                Body2(
                    text = author,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.m, top = Padding.s, end = Padding.m, bottom = Padding.m)
                )
            }
        }
        MiniButton(
            onClick = onShareClick,
            modifier = Modifier.align(Alignment.BottomEnd),
            id = R.drawable.ic_share
        )
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    QuoteCard(quote = "It does not matter how slowly you go as long as you do not stop.", author = "Confucius")
}
