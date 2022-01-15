package com.guness.lottie.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import com.guness.lottie.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber

class ErrorHandler(private val context: Context?, private val message: ImmediateTitle = Title.Resource(R.string.unexpected_error)) {
    fun handleError(error: Throwable) {
        Timber.e(error, "Error logged, please review later on")
        context ?: return

        MainScope().launch {
            if ((context as? Activity?)?.isFinishing != true) {
                Toast.makeText(context, message.immediate(context), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun headlessErrorHandler(message: ImmediateTitle = Title.Resource(R.string.unexpected_error), handler: Callback<Throwable>? = null) =
    (null as Context?).errorHandler(message, handler)

fun Fragment.errorHandler(message: ImmediateTitle = Title.Resource(R.string.unexpected_error), handler: Callback<Throwable>? = null) =
    context.errorHandler(message, handler)

@Composable
fun errorHandler(message: ImmediateTitle = Title.Resource(R.string.unexpected_error), handler: Callback<Throwable>? = null) =
    LocalContext.current.errorHandler(message, handler)

fun Context?.errorHandler(message: ImmediateTitle = Title.Resource(R.string.unexpected_error), handler: Callback<Throwable>? = null) =
    CoroutineExceptionHandler { _, exception ->
        if (handler == null) {
            ErrorHandler(this, message).handleError(exception)
        } else {
            handler(exception)
        }
    }

