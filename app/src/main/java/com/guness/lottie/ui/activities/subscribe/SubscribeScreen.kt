package com.guness.lottie.ui.activities.subscribe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.BuildConfig
import com.guness.lottie.R
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.extensions.openUriSafe
import com.guness.lottie.utils.widget.TopBackground

/**
 * Created by guness on 17.11.2021 10:43
 */
@Composable
fun SubscribeScreen(viewModel: SubscribeViewModel = hiltViewModel()) {

    val features = stringArrayResource(id = R.array.home_premium_checks)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        LazyColumn(
            contentPadding = PaddingValues(bottom = BottomBarHeight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(
                    modifier = Modifier
                        .padding(Padding.m)
                        .size(AvatarSize)
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_signature), contentDescription = null)
                }
            }
            item {
                Title1(
                    text = stringResource(id = R.string.subscribe_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Padding.s),
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
            items(features) { feature ->
                FeatureCard(
                    text = feature,
                    modifier = Modifier.padding(start = Padding.s, top = Padding.s, end = Padding.s)
                )
            }
            item {
                BigButton(
                    onClick = { /*TODO*/ },
                    color = ButtonColor.Pink,
                    text = "Monthly 9,99" /*TODO*/,
                    modifier = Modifier.padding(start = Padding.l, top = Padding.m, end = Padding.l)
                )
            }
            item {
                BigButton(
                    onClick = { /*TODO*/ },
                    text = "Start 3 days free trial" /*TODO*/,
                    overline = "Annual 139,99" /*TODO*/,
                    modifier = Modifier.padding(start = Padding.l, top = Padding.xs, end = Padding.l)
                )
            }
            item {
                LabelButton(
                    onClick = { /*TODO*/ },
                    text = stringResource(R.string.subscribe_restore),
                    modifier = Modifier.padding(start = Padding.l, top = Padding.xs, end = Padding.l)
                )
            }

            item {
                val uriHandler = LocalUriHandler.current
                val context = LocalContext.current
                Row(
                    modifier = Modifier.padding(horizontal = Padding.xs),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LabelButton(
                        onClick = { uriHandler.openUriSafe(BuildConfig.URL_PRIVACY, context) },
                        text = stringResource(R.string.subscribe_privacy),
                        modifier = Modifier.weight(1f),
                    )
                    LabelButton(
                        onClick = { uriHandler.openUriSafe(BuildConfig.URL_TOS, context) },
                        text = stringResource(R.string.subscribe_tos),
                        modifier = Modifier.weight(1f),
                    )
                }
            }
            item {
                Footnote2(
                    text = stringResource(R.string.subscribe_explained),
                    modifier = Modifier
                        .padding(horizontal = Padding.l)
                        .alpha(0.6f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 1200)
@Composable
private fun SubscribePreview() = LottieTheme {
    SubscribeScreen(SubscribeViewModel())
}
