package com.guness.lottie.data.useCases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

/**
 * Created by guness on 12.01.2022 15:00
 */
abstract class UseCase<T> {
    protected val _data = MutableStateFlow<T?>(null)
    val data: Flow<T> = _data.filterNotNull()

    abstract suspend fun update()
}
