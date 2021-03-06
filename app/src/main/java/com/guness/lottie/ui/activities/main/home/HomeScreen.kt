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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.guness.lottie.R
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.data.dto.User
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.extensions.openUriSafe
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun HomeScreen(onAnimationClick: OnClick<Long>, viewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = "data") {
        viewModel.loadData()
    }
    ScreenContent(onAnimationClick, viewModel.user, viewModel.featured, viewModel.animators, viewModel.blogs)
}

@Composable
private fun ScreenContent(
    onAnimationClick: OnClick<Long>,
    _user: Flow<User?>,
    _featured: Flow<List<Animation>>,
    _animators: Flow<List<Animator>>,
    _blogs: Flow<List<Blog>>,
) {

    val user by _user.collectAsState(initial = null)
    val featured by _featured.collectAsState(initial = emptyList())
    val animators by _animators.collectAsState(initial = emptyList())
    val blogs by _blogs.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        LazyColumn(contentPadding = PaddingValues(bottom = BottomBarHeight)) {
            item {
                LargeTitle(
                    text = if (user == null) {
                        stringResource(id = R.string.home_welcome)
                    } else {
                        stringResource(id = R.string.home_welcome_x, user!!.name)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.xxl)
                )
            }
            item {
                HeroCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Padding.s, top = Padding.m, end = Padding.s)
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
                FeaturedRow(
                    animations = featured,
                    modifier = Modifier.padding(top = Padding.s),
                    onAnimationClick = onAnimationClick
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

            items(blogs) { blog->
                val context = LocalContext.current
                val uriHandler = LocalUriHandler.current
                BlogCard(
                    blog = blog,
                    modifier = Modifier
                        .padding(start = Padding.s, top = Padding.s, end = Padding.s)
                ) {
                    uriHandler.openUriSafe(blog.imageUrl, context)
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 1600)
@Composable
private fun HomePreview() = LottieTheme {
    ScreenContent({}, emptyFlow(), emptyFlow(), emptyFlow(), emptyFlow())
}
