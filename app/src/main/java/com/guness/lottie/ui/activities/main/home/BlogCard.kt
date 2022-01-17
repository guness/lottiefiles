package com.guness.lottie.ui.activities.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.ui.theme.Footnote1
import com.guness.lottie.ui.theme.LottieTheme
import com.guness.lottie.ui.theme.Padding
import com.guness.lottie.utils.OnClick
import java.time.Instant

/**
 * Created by guness on 14.01.2022 12:54
 */

private val WIDTH = 180.dp
private val HEIGHT = 156.dp

@Composable
fun BlogCard(blog: Blog, modifier: Modifier = Modifier, onBlogClick: OnClick<Blog> = { }) {

    Card(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onBlogClick(blog) }) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.onBackground)
                    .size(WIDTH, HEIGHT)
            ) {
                Image(
                    painter = rememberImagePainter(blog.imageUrl),
                    contentDescription = null,
                    modifier = Modifier.height(HEIGHT),
                    contentScale = ContentScale.Crop
                )
            }
            Footnote1(
                text = blog.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Padding.s, top = Padding.xs, end = Padding.xs),
                maxLines = 5
            )
        }
    }
}

@Preview
@Composable
private fun CardPreview() = LottieTheme {
    BlogCard(Blog(123, "Blog Title", Instant.now(), "imageUrl"))
}
