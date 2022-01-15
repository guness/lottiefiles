package com.guness.lottie.ui.activities.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Created by guness on 15.01.2022 22:10
 */
@Composable
fun AnimationScreen(viewModel: AnimationViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = "data") {
        viewModel.loadData()
    }
    ScreenContent(viewModel.popular)
}

@Composable
private fun ScreenContent(
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
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AnimationPreview() = LottieTheme {
    ScreenContent(emptyFlow())
}

