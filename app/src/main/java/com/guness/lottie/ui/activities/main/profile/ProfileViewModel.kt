package com.guness.lottie.ui.activities.main.profile

import androidx.lifecycle.ViewModel
import com.guness.lottie.data.dto.User
import com.guness.lottie.data.repo.UserRepository
import com.guness.lottie.data.useCases.LoginUseCase
import com.guness.lottie.data.useCases.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by guness on 14.11.2021 20:39
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepository: UserRepository, val login: LoginUseCase, val logoutUser: LogoutUseCase) : ViewModel() {

    val user: Flow<User?>
        get() = userRepository.user

    suspend fun login(name: String) {
        delay(200)
        login(User(name = name, bookmarks = emptySet()))
    }

    suspend fun logout() {
        delay(200)
        logoutUser()
    }
}
