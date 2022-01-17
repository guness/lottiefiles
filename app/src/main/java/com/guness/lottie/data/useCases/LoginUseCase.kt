package com.guness.lottie.data.useCases

import com.guness.lottie.data.dto.User
import com.guness.lottie.data.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by guness on 12.01.2022 15:05
 */
class LoginUseCase @Inject constructor(
    private val repository: UserRepository
) : InputUseCase<User>() {

    override suspend fun invoke(value: User) = withContext(Dispatchers.IO) {
        repository.setUser(value)
    }
}
