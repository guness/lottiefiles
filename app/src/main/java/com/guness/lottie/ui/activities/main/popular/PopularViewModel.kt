package com.guness.lottie.ui.activities.main.popular

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
 * Created by guness on 10.11.2021 18:41
 */
@HiltViewModel
class PopularViewModel @Inject constructor(
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
