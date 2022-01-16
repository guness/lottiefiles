package com.guness.lottie.ui.activities.animation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.*
import com.guness.lottie.R
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.MockAnimation
import com.guness.lottie.data.useCases.ShareAnimationUseCase
import com.guness.lottie.data.useCases.ShowMessageUseCase
import com.guness.lottie.ui.theme.*
import com.guness.lottie.utils.extensions.toColor
import com.guness.lottie.utils.widget.TopBackground
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by guness on 15.01.2022 22:10
 */

private val SIZE = 48.dp

@Composable
fun AnimationScreen(animationId: Long?, viewModel: AnimationViewModel = hiltViewModel()) {

    if (animationId != null) {
        LaunchedEffect(key1 = "animation/$animationId") {
            viewModel.loadData(animationId)
        }
    }

    val animation by viewModel.animation.collectAsState(initial = null)

    ScreenContent(animation)
}

@Composable
private fun ScreenContent(animation: Animation?) {
    var isPlaying by remember { mutableStateOf(false) }

    if (animation != null) {
        LaunchedEffect(key1 = "delay/${animation.id}") {
            delay(250)
            isPlaying = true
        }
    } else {
        isPlaying = false
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        TopBackground()
        Column {

            if (animation != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface.copy(alpha = TransparentAlpha))
                        .padding(Padding.s),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        Image(
                            painter = rememberImagePainter(animation.createdBy.avatarUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(SIZE)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Body1(
                                text = animation.name,
                                modifier = Modifier.padding(start = Padding.xs, end = Padding.xs)
                            )
                            Footnote2(
                                text = animation.createdBy.name,
                                modifier = Modifier.padding(start = Padding.xs, end = Padding.xs)
                            )
                        }
                    }
                    MiniButton(
                        onClick = {
                            isPlaying = !isPlaying
                        },
                        id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                    )
                }
            }

            val composition by rememberLottieComposition(
                if (animation == null) {
                    LottieCompositionSpec.RawRes(R.raw.shimmer)
                } else {
                    LottieCompositionSpec.Url(animation.lottieUrl)
                }
            )

            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying,
                iterations = LottieConstants.IterateForever
            )

            LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())

            LottieAnimation(
                composition,
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(animation?.bgColor?.toColor() ?: MaterialTheme.colors.background),
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BottomBarHeight),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                MiniButton(
                    onClick = {
                        if (animation != null) {
                            scope.launch {
                                ShareAnimationUseCase(context, animation).invoke()
                            }
                        }
                    },
                    id = R.drawable.ic_share
                )
                MiniButton(
                    onClick = {
                        scope.launch {
                            ShowMessageUseCase(context, R.string.to_be_implemented).invoke()
                        }
                    }, id = R.drawable.ic_bookmark
                )
                AnimatedMiniButton(onClick = {}, id = R.raw.like)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AnimationPreview() = LottieTheme {
    ScreenContent(MockAnimation)
}
