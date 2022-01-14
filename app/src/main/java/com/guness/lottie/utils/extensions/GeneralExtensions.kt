package com.guness.lottie.utils.extensions

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.UriHandler
import com.guness.lottie.core.LottieActivity
import com.guness.lottie.utils.ErrorHandler

/**
 * Created by guness on 22.11.2021 14:05
 */
fun UriHandler.openUriSafe(uri: String, context: Context) = try {
    openUri(uri)
} catch (e: Exception) {
    ErrorHandler(context).handleError(e)
}

@Composable
fun isPreview(): Boolean {
    var context = LocalContext.current
    while (context is ContextWrapper) {
        if (context is LottieActivity) return false
        context = context.baseContext
    }
    return true
}

fun String.toColor() = Color(removePrefix("#").padStart(8, 'F').toLong(16))