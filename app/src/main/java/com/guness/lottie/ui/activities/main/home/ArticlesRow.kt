package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.guness.lottie.R
import com.guness.lottie.data.dto.Article
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick

private val WIDTH = 129.dp

@Composable
fun ArticlesRow(articles: List<Article>, modifier: Modifier = Modifier, loading: Boolean = false, onArticleClick: (Article) -> Unit = {}) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Padding.s),
        horizontalArrangement = Arrangement.spacedBy(Padding.s)
    ) {
        if (loading && articles.isEmpty()) {
            items(5) {
                ArticlePlaceholder()
            }
        } else {
            items(articles) {
                ArticleView(it) { onArticleClick(it) }
            }
        }
    }
}

@Composable
fun ArticlePlaceholder() {
    @Composable
    fun Line(width: Dp = WIDTH) = Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.onBackground)
            .size(width = width, height = 10.dp)
    )

    Column {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Radius.s))
                .background(MaterialTheme.colors.onBackground)
                .size(WIDTH)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Line()
        Spacer(modifier = Modifier.height(8.dp))
        Line()
        Spacer(modifier = Modifier.height(8.dp))
        Line()
        Spacer(modifier = Modifier.height(8.dp))
        Line(width = 90.dp)
    }
}

@Composable
fun ArticleView(article: Article, onArticleClick: OnClick = {}) {
    Column(
        modifier = Modifier
            .width(WIDTH)
            .clickable(onClick = onArticleClick)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Radius.s))
                .background(MaterialTheme.colors.onBackground)
                .size(WIDTH)
        ) {
            Image(
                painter = rememberImagePainter(article.image),
                contentDescription = null,
                modifier = Modifier.size(WIDTH),
                contentScale = ContentScale.Crop
            )
            MiniButton(
                onClick = onArticleClick,
                modifier = Modifier.align(Alignment.TopEnd),
                id = R.drawable.ic_link
            )
        }
        Box(modifier = Modifier.padding(top = Padding.s, end = Padding.m)) {
            Footnote1(text = article.text ?: "-", maxLines = 3)
        }
    }
}

@Preview
@Composable
private fun PlaceholderPreview() = LottieTheme {
    ArticlePlaceholder()
}

@Preview(showBackground = true)
@Composable
private fun ArticlePreview() = LottieTheme {
    ArticleView(Article(text = "text1", image = "image1", url = "url1"))
}

@Preview(showBackground = true)
@Composable
private fun RowPreview() = LottieTheme {
    ArticlesRow(
        listOf(
            Article(text = "text1", image = "image1", url = "url1"),
            Article(text = "text2", image = "image2", url = "url2"),
            Article(text = "text3", image = "image3", url = "url3")
        )
    )
}
