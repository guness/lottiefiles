package com.guness.lottie.ui.activities.main.recent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.guness.lottie.data.dto.MockAnimation
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.OnClick
import com.guness.lottie.utils.extensions.toColor

/**
 * Created by guness on 8.11.2021 11:59
 */
private val SIZE = 24.dp

@Composable
fun RecentCard(modifier: Modifier = Modifier, animation: Animation, onAnimationClick: OnClick<Long> = {}) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Radius.l))
            .background(MaterialTheme.colors.surface.copy(alpha = TransparentAlpha))
            .clickable { onAnimationClick(animation.id) }
    ) {
        Card {
            val composition by rememberLottieComposition(LottieCompositionSpec.Url(animation.lottieUrl))
            LottieAnimation(
                composition,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(animation.bgColor.toColor())
                    .aspectRatio(GoldenRatio),
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Fit
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.xs),
            horizontalArrangement = Arrangement.SpaceBetween,
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
            Spacer(modifier = Modifier.width(Padding.xs))
            Footnote2(text = animation.name, maxLines = 1)
        }
    }
}

@Preview(widthDp = 164)
@Composable
private fun CardPreview() = LottieTheme {
    RecentCard(animation = MockAnimation)
}
