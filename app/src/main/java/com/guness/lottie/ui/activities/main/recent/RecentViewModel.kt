package com.guness.lottie.ui.activities.main.recent

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.repo.AnimationRepository
import com.guness.lottie.data.useCases.FetchRecentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * Created by guness on 8.11.2021 11:42
 */
@HiltViewModel
class RecentViewModel @Inject constructor(
    private val animationRepository: AnimationRepository,
    private val fetchRecent: FetchRecentUseCase,
) : ViewModel() {

    val recent: Flow<List<Animation>>
        get() = animationRepository.recent

    suspend fun loadData() = supervisorScope {
        joinAll(
            async { fetchRecent() }
        )
    }
}
