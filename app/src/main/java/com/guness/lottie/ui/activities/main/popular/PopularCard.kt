package com.guness.lottie.ui.activities.main.popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.extensions.toColor
import java.time.Instant

/**
 * Created by guness on 8.11.2021 11:59
 */

private val SIZE = 32.dp

@Composable
fun PopularCard(modifier: Modifier = Modifier, animation: Animation, onCategoryClick: OnClick = {}) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(GoldenRatio)

    ) {
        Box {
            val composition by rememberLottieComposition(LottieCompositionSpec.Url(animation.lottieUrl))
            LottieAnimation(
                composition,
                modifier = Modifier
                    .fillMaxSize()
                    .background(animation.bgColor.toColor()),
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = Radius.l))
                    .background(MaterialTheme.colors.surface.copy(alpha = TransparentAlpha))
                    .padding(Padding.xs),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(animation.createdBy.avatarUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(SIZE)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Caption1(text = animation.createdBy.name, modifier = Modifier.padding(Padding.xs))
            }
            Footnote2(
                text = animation.name, modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(topStart = Radius.l))
                    .background(MaterialTheme.colors.surface.copy(alpha = TransparentAlpha))
                    .padding(Padding.xs)
            )
        }
    }
}

@Preview(widthDp = 264)
@Composable
private fun CardPreview() = LottieTheme {
    PopularCard(animation = mockAnimation)
}

private val mockAnimation = Animation(
    id = 0,
    name = "Name One",
    bgColor = "#FFBBEE",
    lottieUrl = "lottieUrl",
    gifUrl = null,
    videoUrl = null,
    imageUrl = "imageUrl",
    createdAt = Instant.now(),
    createdBy = Animator(id = 0, name = "name", avatarUrl = "avatarUrl")
)