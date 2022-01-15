package com.guness.lottie.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guness.lottie.R
import com.guness.lottie.utils.Callback

@Composable
fun MiniButton(
    onClick: Callback<Unit>, modifier: Modifier = Modifier, @DrawableRes id: Int
) = IconButton(
    onClick = { onClick(Unit) },
    modifier = modifier
        .padding(Padding.s)
        .size(Padding.l)
        .background(MaterialTheme.colors.primary, shape = RoundedCornerShape(Radius.s))
) {
    Icon(
        modifier = Modifier.size(Padding.m),
        painter = painterResource(id),
        contentDescription = stringResource(R.string.share),
        tint = Color.White,
    )
}

@Composable
fun BigButton(
    onClick: Callback<Unit>,
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
    onClick: Callback<Unit>, text: String, modifier: Modifier = Modifier
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
    MiniButton(onClick = {}, id = R.drawable.ic_link)
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