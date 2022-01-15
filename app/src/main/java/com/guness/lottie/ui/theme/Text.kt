package com.guness.lottie.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun LargeTitle(text: String, modifier: Modifier = Modifier) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.onPrimary,
    fontWeight = FontWeight.Bold,
    fontSize = 34.sp
)

@Composable
fun Title1(text: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colors.secondary, textAlign: TextAlign? = null) = Text(
    text = text,
    modifier = modifier,
    color = color,
    fontWeight = FontWeight.Bold,
    fontSize = 28.sp,
    textAlign = textAlign
)

@Composable
fun Title2(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.secondary,
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    textAlign = textAlign
)

@Composable
fun Body1(text: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colors.primaryVariant, textAlign: TextAlign? = null) = Text(
    text = text,
    modifier = modifier,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    color = color,
    textAlign = textAlign
)

@Composable
fun Body2(text: String, modifier: Modifier = Modifier) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.primaryVariant,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

@Composable
fun Footnote1(text: String, modifier: Modifier = Modifier, maxLines: Int = Int.MAX_VALUE) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.primaryVariant,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    overflow = TextOverflow.Ellipsis,
    maxLines = maxLines,
)

@Composable
fun Footnote2(text: String, modifier: Modifier = Modifier, textAlign: TextAlign? = null, maxLines: Int = Int.MAX_VALUE) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.primaryVariant,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    textAlign = textAlign,
    maxLines = maxLines
)

@Composable
fun Caption1(text: String, modifier: Modifier = Modifier) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.secondaryVariant,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp
)

@Composable
fun Caption2(text: String, modifier: Modifier = Modifier, maxLines: Int = Int.MAX_VALUE) = Text(
    text = text,
    modifier = modifier,
    color = MaterialTheme.colors.secondaryVariant,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    maxLines = maxLines,
)