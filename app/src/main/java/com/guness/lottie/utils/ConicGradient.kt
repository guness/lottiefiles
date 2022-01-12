package com.guness.lottie.utils

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import com.google.android.material.math.MathUtils

@Stable
fun conicGradient(
    vararg colorStops: Pair<Int, Color>,
    offset: Offset = Offset.Unspecified,
    rotation: Int = 0,
): Brush = ConicGradient(
    colors = colorStops.map { it.second },
    stops = colorStops.map { it.first },
    offset = offset,
    rotation = rotation
)

@Immutable
class ConicGradient internal constructor(
    private val offset: Offset,
    private val rotation: Int,
    private val colors: List<Color>,
    private val stops: List<Int>
) : ShaderBrush() {

    private val colorStops: MutableList<Pair<Int, Color>>

    init {
        assert(colors.first() == colors.last()) {
            "Starting and ending colors should be same"
        }
        assert(stops.first().rem(360) == stops.last().rem(360)) {
            "Starting and ending angles should be same"
        }

        colorStops = stops
            .asSequence()
            .map { (it + rotation + 360).rem(360) }
            .mapIndexed { index, value -> value to colors[index] }
            .distinct()
            .sortedBy { it.first }
            .toMutableList()

        val firstStop = colorStops.first()
        val lastStop = colorStops.last()

        when {
            firstStop.first == 0 -> colorStops.add(360 to firstStop.second)
            lastStop.first == 360 -> colorStops.add(0, 0 to lastStop.second)
            else -> {
                val zeroColor = lerpZeroColor(firstStop, lastStop)
                colorStops.add(0, 0 to zeroColor)
                colorStops.add(360 to zeroColor)
            }
        }
    }

    private fun lerpZeroColor(min: Pair<Int, Color>, max: Pair<Int, Color>): Color {

        val amount = calculateAmount(min.first, max.first)
        val r = MathUtils.lerp(max.second.red, min.second.red, amount)
        val g = MathUtils.lerp(max.second.green, min.second.green, amount)
        val b = MathUtils.lerp(max.second.blue, min.second.blue, amount)
        val a = MathUtils.lerp(max.second.alpha, min.second.alpha, amount)

        return Color(r, g, b, a)
    }

    private fun calculateAmount(first: Int, last: Int): Float {
        val total = (360 + first - last).rem(360).toFloat()
        return (360 - last) / total
    }

    override fun createShader(size: Size): Shader =
        SweepGradientShader(
            if (offset.isUnspecified) {
                size.center
            } else {
                Offset(
                    size.width * offset.x,
                    size.height * offset.y
                )
            },
            colorStops.map { it.second },
            colorStops.map { it.first / 360f }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConicGradient) return false

        if (offset != other.offset) return false
        if (rotation != other.rotation) return false
        if (colors != other.colors) return false
        if (stops != other.stops) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offset.hashCode()
        result = 31 * result + colors.hashCode()
        result = 31 * result + rotation.hashCode()
        result = 31 * result + stops.hashCode()
        return result
    }

    override fun toString(): String {
        val offsetValue = if (offset.isSpecified) "offset=$offset, " else ""
        val rotationValue = if (rotation != 0) "rotation=$offset, " else ""
        return "SweepGradient($offsetValue${rotationValue}colors=$colors, stops=$stops)"
    }
}