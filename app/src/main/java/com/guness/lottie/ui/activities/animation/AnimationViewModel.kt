package com.guness.lottie.ui.activities.animation

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.repo.AnimationRepository
import com.guness.lottie.data.useCases.FetchPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * Created by guness on 15.01.2022 22:10
 */
@HiltViewModel
class AnimationViewModel @Inject constructor(
    private val animationRepository: AnimationRepository,
    private val fetchPopular: FetchPopularUseCase,
) : ViewModel() {

    val popular: Flow<List<Animation>>
        get() = animationRepository.popular

    suspend fun loadData() = supervisorScope {
        joinAll(
            async { fetchPopular() }
        )
    }
}