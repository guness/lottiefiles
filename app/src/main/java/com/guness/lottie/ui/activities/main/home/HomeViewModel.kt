package com.guness.lottie.ui.activities.main.home

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.repo.AnimatorRepository
import com.guness.lottie.data.useCases.FetchAnimatorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animatorRepository: AnimatorRepository,
    private val fetchAnimators: FetchAnimatorsUseCase
) : ViewModel() {

    val animators: Flow<List<Animator>>
        get() = animatorRepository.animators

    suspend fun loadAnimators() = fetchAnimators()
}
