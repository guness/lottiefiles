package com.guness.lottie.data.useCases

/**
 * Created by guness on 12.01.2022 15:00
 */
abstract class UseCase<T> {
    abstract suspend fun update()
}
