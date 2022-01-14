package com.guness.lottie.ui.activities.main.home

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
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomeScreen(onPractice: OnClick, onSubscribe: OnClick, viewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = "data") {
        viewModel.loadData()
    }
    ScreenContent(onPractice, onSubscribe, viewModel.animators, viewModel.blogs)
}

@Composable
private fun ScreenContent(
    onPractice: OnClick, onSubscribe: OnClick,
    _animators: Flow<List<Animator>>,
    _blogs: Flow<List<Blog>>,
) {

    val animators by _animators.collectAsState(initial = emptyList())
    val blogs by _blogs.collectAsState(initial = emptyList())

    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(start = Padding.s, top = Padding.m, end = Padding.s)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        LazyColumn(contentPadding = PaddingValues(bottom = BottomBarHeight)) {
            item {
                //TODO: Title
                LargeTitle(
                    text = "Good Morning, Sinan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl)
                )
            }
            item {
                DailyCard(
                    modifier = cardModifier,
                    onStartClick = onPractice,
                    onSettingsClick = { /*TODO*/ }
                )
            }
            item {
                Title2(
                    text = stringResource(id = R.string.home_featured),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl)
                )
            }
            item {
                //TODO: quote and author
                QuoteCard(
                    quote = "It does not matter how slowly you go as long as you do not stop.",
                    author = "Confucius",
                    modifier = cardModifier,
                    onShareClick = { /*TODO*/ }
                )
            }
            item {
                Title2(
                    text = stringResource(id = R.string.home_animators),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl)
                )
            }
            item {
                AnimatorsRow(
                    animators = animators,
                    modifier = Modifier.padding(top = Padding.s)
                )
            }
            item {
                Title2(
                    text = stringResource(id = R.string.home_blogs),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl)
                )
            }
            items(blogs) {
                BlogCard(
                    blog = it,
                    modifier = Modifier
                        .padding(start = Padding.s, top = Padding.s, end = Padding.s)
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 1600)
@Composable
private fun HomePreview() = LottieTheme {
    ScreenContent({}, {}, emptyFlow(), emptyFlow())
}
