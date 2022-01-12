package com.guness.lottie.utils

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface Titled {
    val title: Title
}

interface ImmediateTitle {
    fun immediate(context: Context): String
}

sealed class Title {
    class Resource(val id: Int, private vararg val params: Any) : Title(), ImmediateTitle {
        override fun immediate(context: Context) = if (params.isEmpty()) {
            context.getString(id)
        } else {
            context.getString(id, *params)
        }
    }

    class Text(val value: String) : Title(), ImmediateTitle {
        override fun immediate(context: Context) = value
    }

    class TextFlow(val flow: Flow<String> = flowOf("")) : Title()

    fun getText(context: Context): Flow<CharSequence> {
        return when (this) {
            is Text -> flowOf(immediate(context))
            is Resource -> flowOf(immediate(context))
            is TextFlow -> flow
        }
    }
}
