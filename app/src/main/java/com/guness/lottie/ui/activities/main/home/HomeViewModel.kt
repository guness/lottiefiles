package com.guness.lottie.ui.activities.main.home

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.data.repo.AnimatorRepository
import com.guness.lottie.data.repo.BlogRepository
import com.guness.lottie.data.useCases.FetchAnimatorsUseCase
import com.guness.lottie.data.useCases.FetchBlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animatorRepository: AnimatorRepository,
    private val blogRepository: BlogRepository,
    private val fetchAnimators: FetchAnimatorsUseCase,
    private val fetchBlogs: FetchBlogUseCase
) : ViewModel() {

    val animators: Flow<List<Animator>>
        get() = animatorRepository.animators

    val blogs: Flow<List<Blog>>
        get() = blogRepository.blogs

    suspend fun loadData() = supervisorScope {
        joinAll(
            async { fetchAnimators() },
            async { fetchBlogs() }
        )
    }
}
