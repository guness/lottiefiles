package com.guness.lottie.ui.activities.main.popular

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.R
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.ui.theme.BottomBarHeight
import com.guness.lottie.ui.theme.LargeTitle
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun PopularScreen(onAnimationClick: OnClick<Long>, viewModel: PopularViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = "data") {
        viewModel.loadData()
    }
    ScreenContent(onAnimationClick, viewModel.popular)
}

@Composable
private fun ScreenContent(
    onAnimationClick: OnClick<Long>,
    _popular: Flow<List<Animation>>,
) {

    val popular by _popular.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        LazyColumn(contentPadding = PaddingValues(bottom = BottomBarHeight)) {
            item {
                LargeTitle(
                    text = stringResource(id = R.string.nav_popular),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl, bottom = Padding.m)
                )
            }
            items(popular) {
                PopularCard(
                    animation = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, end = Padding.s, bottom = Padding.s),
                    onAnimationClick = onAnimationClick
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PopularPreview() = LottieTheme {
    ScreenContent({}, emptyFlow())
}
