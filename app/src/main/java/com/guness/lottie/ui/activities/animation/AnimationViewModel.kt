package com.guness.lottie.ui.activities.animation

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.repo.AnimationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * Created by guness on 15.01.2022 22:10
 */
@HiltViewModel
class AnimationViewModel @Inject constructor(
    private val animationRepository: AnimationRepository
) : ViewModel() {

    private val _animation = MutableStateFlow<Animation?>(null)
    val animation: Flow<Animation?>
        get() = _animation

    suspend fun loadData(animationId: Long) = supervisorScope {
        _animation.value = null
        joinAll(
            async {
                _animation.value = animationRepository.animation(animationId)
            }
        )
    }
}
