package com.guness.lottie.ui.activities.main.home

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.data.dto.User
import com.guness.lottie.data.repo.AnimationRepository
import com.guness.lottie.data.repo.AnimatorRepository
import com.guness.lottie.data.repo.BlogRepository
import com.guness.lottie.data.repo.UserRepository
import com.guness.lottie.data.useCases.FetchAnimatorsUseCase
import com.guness.lottie.data.useCases.FetchBlogUseCase
import com.guness.lottie.data.useCases.FetchFeaturedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animationRepository: AnimationRepository,
    private val animatorRepository: AnimatorRepository,
    private val blogRepository: BlogRepository,
    private val userRepository: UserRepository,
    private val fetchFeatured: FetchFeaturedUseCase,
    private val fetchAnimators: FetchAnimatorsUseCase,
    private val fetchBlogs: FetchBlogUseCase
) : ViewModel() {

    val user: Flow<User?>
        get() = userRepository.user

    val featured: Flow<List<Animation>>
        get() = animationRepository.featured

    val animators: Flow<List<Animator>>
        get() = animatorRepository.animators

    val blogs: Flow<List<Blog>>
        get() = blogRepository.blogs

    suspend fun loadData() = supervisorScope {
        joinAll(
            async { fetchFeatured() },
            async { fetchAnimators() },
            async { fetchBlogs() }
        )
    }
}
