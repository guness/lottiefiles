package com.guness.lottie.data.useCases

import com.guness.lottie.data.dto.User
import com.guness.lottie.data.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by guness on 17.01.2022 13:31
 */
class LogoutUseCase @Inject constructor(
    private val repository: UserRepository
) : UseCase<User>() {

    override suspend fun invoke() = withContext(Dispatchers.IO) {
        repository.logout()
    }
}
