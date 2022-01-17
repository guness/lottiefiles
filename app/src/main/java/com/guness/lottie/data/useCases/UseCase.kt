package com.guness.lottie.data.useCases

/**
 * Created by guness on 12.01.2022 15:00
 */
abstract class UseCase<T> {
    abstract suspend operator fun invoke()
}

abstract class InputUseCase<T> {
    abstract suspend operator fun invoke(value: T)
}
