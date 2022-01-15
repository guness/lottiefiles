package com.guness.lottie.utils.extensions

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandler
import com.guness.lottie.utils.ErrorHandler

/**
 * Created by guness on 22.11.2021 14:05
 */
fun UriHandler.openUriSafe(uri: String, context: Context) = try {
    openUri(uri)
} catch (e: Exception) {
    ErrorHandler(context).handleError(e)
}

fun String.toColor() = try {
    Color(removePrefix("#").padStart(8, 'F').toLong(16))
} catch (e: Exception) {
    Color.White
}
