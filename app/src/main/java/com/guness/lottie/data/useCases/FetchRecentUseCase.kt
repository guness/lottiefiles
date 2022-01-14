package com.guness.lottie.data.useCases

import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.repo.AnimationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by guness on 12.01.2022 15:05
 */
class FetchRecentUseCase @Inject constructor(
    private val repository: AnimationRepository
) : UseCase<List<Animator>>() {

    override suspend fun invoke() = withContext(Dispatchers.IO) {
        repository.updateRecent()
    }
}
