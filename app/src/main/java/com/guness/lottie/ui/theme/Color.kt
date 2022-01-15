package com.guness.lottie.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.guness.lottie.utils.conicGradient

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LottiePink = Color(0xFFF8BBD0)

val DayThemeAccent = Color(0xFF5F57CF)
val DayThemeBody = Color(0xFF322E5F)
val DayThemeCards = Color.White
val DayThemeBackground = Color(0xFFECECEC)
val DayThemeSeparator = Color(0xFFC6C6C8)
val DayThemeGrey = Color(0xFF5E5E5E)
val DayThemeTitles = Color(0xFF201C54)

val NightThemeAccent = Color(0xFF6357FF)
val NightThemeBody = Color(0xFFD4CEFF)
val NightThemeCards = DayThemeTitles
val NightThemeBackground = Color(0xFF1B173D)
val NightThemeSeparator = Color(0xFF47446F)
val NightThemeGrey = Color(0xFFCDC8FA)
val NightThemeTitles = DayThemeCards

const val TransparentAlpha = 0.5f

private val gradientColors = arrayOf(
    0 to Color(0xFFF8BBD0),
    42 to Color(0xFF8C9EFF),
    251 to Color(0xFFAF52BF),
    309 to Color(0xFF9C27B0),
    360 to Color(0xFFF8BBD0),
)

fun lottieGradient(offset: Offset = Offset.Unspecified, rotation: Int = 0): Brush {
    return conicGradient(
        colorStops = gradientColors,
        offset = offset,
        // Css - Android difference in degrees
        rotation = rotation - 90
    )
}
