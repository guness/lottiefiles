package com.guness.lottie.data.repo

import com.guness.lottie.data.db.UserDao
import com.guness.lottie.data.dto.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by guness on 17.01.2022 10:55
 */
@Singleton
class UserRepository @Inject constructor(private val dao: UserDao) {

    val user = dao.user()

    suspend fun getUser() = dao.get()

    suspend fun setUser(user: User) = dao.set(user)

    suspend fun logout() = dao.clear()

    suspend fun hasLogin() = getUser() != null

    suspend fun setName(name: String) = dao.setName(name)

    suspend fun setBookmark(animationId: Long) = dao.setBookmark(animationId)

    suspend fun removeBookmark(animationId: Long) = dao.removeBookmark(animationId)
}
