package com.guness.lottie.ui.activities.main.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.R
import com.guness.lottie.data.db.ArticleDao
import com.guness.lottie.data.dto.*
import com.guness.lottie.data.useCases.AnimatorsUseCase
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.ui.theme.BottomBarHeight
import com.guness.lottie.ui.theme.LargeTitle
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.compose.items
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CategoryScreen(onSubscribe: OnClick, viewModel: CategoryViewModel = hiltViewModel()) {

    val categories = viewModel.categories

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        LazyColumn(contentPadding = PaddingValues(bottom = BottomBarHeight)) {
            item {
                LargeTitle(
                    text = stringResource(id = R.string.nav_category),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl, bottom = Padding.m)
                )
            }
            items(categories, 2) { category ->
                CategoryCard(
                    category = category,
                    modifier = Modifier.padding(Padding.xs)
                ) { onSubscribe() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryPreview() = LottieTheme {
    val webservice = object : ApiWebservice {
        override suspend fun getFeaturedAnimations(): ApiResponse<Animation> = ApiResponse()

        override suspend fun getPopularAnimations(): ApiResponse<Animation> = ApiResponse()

        override suspend fun getRecentAnimations(): ApiResponse<Animation> = ApiResponse()

        override suspend fun getAnimators(): ApiResponse<Animator> = ApiResponse()

        override suspend fun getBlogs(): ApiResponse<Blog> = ApiResponse()
    }
    val dao = object : ArticleDao {
        override suspend fun add(articles: Article) = Unit

        override fun observeAll(): Flow<List<Article>> = emptyFlow()

        override suspend fun clear() = Unit

    }
    val animators = AnimatorsUseCase(webservice, dao)
    CategoryScreen({}, CategoryViewModel(animators))
}
