package com.guness.lottie.utils.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.guness.lottie.ui.theme.Padding

/**
 * Created by guness on 14.11.2021 21:00
 */
inline fun <T> LazyListScope.items(list: List<T>, column: Int, crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit) {
    val rows = (list.size + column - 1) / column

    items(rows) { rowIndex ->
        Row(modifier = Modifier.padding(horizontal = Padding.xs)) {
            for (columnIndex in 0 until column) {
                val itemIndex = rowIndex * column + columnIndex
                if (itemIndex < list.size) {
                    Box(
                        modifier = Modifier.weight(1f, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(list[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1f, fill = true))
                }
            }
        }
    }
}
