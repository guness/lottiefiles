package com.guness.lottie.ui.activities.main.profile

import com.guness.lottie.LottieUnitTest
import com.guness.lottie.data.useCases.LoginUseCase
import com.guness.lottie.data.useCases.LogoutUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by guness on 17.01.2022 23:53
 */
@RunWith(RobolectricTestRunner::class)
class ProfileViewModelTest : LottieUnitTest() {

    private lateinit var viewModel: ProfileViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = ProfileViewModel(userRepository, LoginUseCase(userRepository), LogoutUseCase(userRepository))
    }

    @Test
    fun testLogin() = runBlocking {
        // Arrange
        assertNull(viewModel.user.first())

        // Act
        viewModel.login("Sinan")

        // Assert
        assertNotNull(viewModel.user.first())
    }

    @Test
    fun testLogout() = runBlocking {
        // Arrange
        viewModel.login("Sinan")
        assertNotNull(viewModel.user.first())

        // Act
        viewModel.logout()

        // Assert
        assertNull(viewModel.user.first())
    }
}