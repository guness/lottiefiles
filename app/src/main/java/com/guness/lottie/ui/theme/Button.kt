package com.guness.lottie.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.guness.lottie.R
import com.guness.lottie.utils.OnClick
import kotlinx.coroutines.launch

@Composable
fun MiniButton(
    onClick: OnClick<Unit>, modifier: Modifier = Modifier, @DrawableRes id: Int
) = IconButton(
    onClick = { onClick(Unit) },
    modifier = modifier
        .padding(Padding.s)
        .size(Padding.l)
        .background(MaterialTheme.colors.primary, shape = Shapes.small)
) {
    Icon(
        modifier = Modifier.size(Padding.m),
        painter = painterResource(id),
        contentDescription = null,
        tint = Color.White,
    )
}

@Composable
fun AnimatedMiniButton(onClick: OnClick<Unit>, modifier: Modifier = Modifier, @RawRes id: Int) {
    var isPlaying by remember { mutableStateOf(false) }
    var iterations by remember { mutableStateOf(1) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))
    val lottieAnimatable = rememberLottieAnimatable()
    val scope = rememberCoroutineScope()

    IconButton(
        onClick = {
            if (isPlaying) {
                iterations += 1
            } else {
                isPlaying = true
            }

            scope.launch {
                lottieAnimatable.resetToBeginning()
            }


            onClick(Unit)
        },
        modifier = modifier
            .size(Padding.l)
            .background(MaterialTheme.colors.primary, shape = Shapes.small)
    ) {


        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(Padding.l),
            isPlaying = isPlaying,
            restartOnPlay = false,
            clipSpec = LottieClipSpec.Progress(0.13f, 0.6f),
            iterations = iterations,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun BigButton(
    onClick: OnClick<Unit>,
    text: String,
    modifier: Modifier = Modifier,
    color: ButtonColor = ButtonColor.Blue,
    overline: String? = null,
    @DrawableRes id: Int? = null
) = Button(
    onClick = { onClick(Unit) },
    modifier = modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 56.dp),
    colors = when (color) {
        is ButtonColor.Blue -> ButtonDefaults.buttonColors()
        is ButtonColor.Pink -> ButtonDefaults.buttonColors(
            backgroundColor = color.color(),
            contentColor = MaterialTheme.colors.onPrimary
        )
    }
) {
    id?.let {
        Icon(
            modifier = Modifier.size(ButtonDefaults.IconSize),
            painter = painterResource(id = it),
            contentDescription = text,
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
    }
    if (overline == null) {
        Text(
            text = text,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )
    } else {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = overline,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
            )
            Text(
                text = text,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun LabelButton(
    onClick: OnClick<Unit>, text: String, modifier: Modifier = Modifier
) = TextButton(
    onClick = { onClick(Unit) },
    modifier = modifier
        .defaultMinSize(minHeight = 48.dp)
) {
    Text(
        text = text,
        modifier = Modifier.padding(Padding.xs),
        color = MaterialTheme.colors.primaryVariant,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
}

@Preview
@Composable
private fun BigButtonPreview() = LottieTheme {
    BigButton(onClick = {}, color = ButtonColor.Pink, text = "Click me")
}

@Preview
@Composable
private fun BigButtonPreview2() = LottieTheme {
    BigButton(onClick = {}, text = "Click me", overline = "Overlined")
}

@Preview
@Composable
private fun MiniButtonPreview() = LottieTheme {
    MiniButton(onClick = {}, id = R.drawable.ic_share)
}

@Preview
@Composable
private fun LabelButtonPreview() = LottieTheme {
    LabelButton(onClick = {}, text = "Restore Purchases")
}

sealed class ButtonColor {
    object Blue : ButtonColor() {
        @Composable
        override fun color(): Color = MaterialTheme.colors.primary
    }

    object Pink : ButtonColor() {
        @Composable
        override fun color(): Color = LottiePink
    }

    @Composable
    abstract fun color(): Color
}