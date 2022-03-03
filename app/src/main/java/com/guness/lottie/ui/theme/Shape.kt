package com.guness.lottie.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(topStart = Radius.s, topEnd = Radius.s, bottomStart = Radius.s),
    medium = RoundedCornerShape(topStart = Radius.l, topEnd = Radius.l, bottomStart = Radius.l),
    large = RoundedCornerShape(0.dp)
)
