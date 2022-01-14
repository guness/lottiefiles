package com.guness.lottie.ui.activities.main.home

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
import com.guness.lottie.data.db.AnimatorDao
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.ApiResponse
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.data.repo.AnimatorRepository
import com.guness.lottie.data.useCases.FetchAnimatorsUseCase
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomeScreen(onPractice: OnClick, onSubscribe: OnClick, viewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = "animators") {
        viewModel.loadAnimators()
    }

    val animators by viewModel.animators.collectAsState(initial = emptyList())

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
                PremiumCard(modifier = cardModifier, onSubscribeClick = onSubscribe)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() = LottieTheme {
    val apiWebservice = object : ApiWebservice {
        override suspend fun getFeaturedAnimations(): ApiResponse<Animation> = ApiResponse()

        override suspend fun getPopularAnimations(): ApiResponse<Animation> = ApiResponse()

        override suspend fun getRecentAnimations(): ApiResponse<Animation> = ApiResponse()

        override suspend fun getAnimators(): ApiResponse<Animator> = ApiResponse()

        override suspend fun getBlogs(): ApiResponse<Blog> = ApiResponse()
    }

    val dao = object : AnimatorDao {
        override suspend fun add(animator: Animator): Long = -1

        override suspend fun addAll(list: List<Animator>) = Unit

        override fun observeAll(): Flow<List<Animator>> = emptyFlow()

        override suspend fun clear() = Unit
    }

    val repo = AnimatorRepository(apiWebservice, dao)

    HomeScreen({}, {}, HomeViewModel(AnimatorRepository(apiWebservice, dao), FetchAnimatorsUseCase(repo)))
}
