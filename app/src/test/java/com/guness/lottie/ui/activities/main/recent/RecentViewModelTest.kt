package com.guness.lottie.ui.activities.main.recent

import com.guness.lottie.LottieUnitTest
import com.guness.lottie.data.useCases.FetchRecentUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

/**
 * Created by guness on 18.01.2022 01:43
 */
class RecentViewModelTest : LottieUnitTest() {

    private lateinit var viewModel: RecentViewModel

    @Before
    override fun setUp() {

        viewModel = RecentViewModel(animationRepository, FetchRecentUseCase(animationRepository))

        val mockedResponse = MockResponse()
        mockedResponse.setResponseCode(200)
        mockedResponse.setBody(
            """
{
  "data": {
    "recent": {
      "currentPage": 1,
      "perPage": 10,
      "totalPages": 3703,
      "from": 1,
      "to": 10,
      "total": 37022,
      "results": [
        {
          "id": 68228,
          "bgColor": "#FFFFFF",
          "lottieUrl": "https://assets7.lottiefiles.com/packages/lf20_ijv6guhj.json",
          "gifUrl": null,
          "videoUrl": null,
          "imageUrl": "https://assets.lottiefiles.com/previews/default.jpg",
          "createdAt": "2021-07-09T03:20:20.000Z",
          "name": "Robotic Animated Font Letter B",
          "createdBy": {
            "avatarUrl": "https://assets9.lottiefiles.com/avatars/300_94765-702240734.jpg",
            "name": "Ilya Gusinski"
          }
        },
        {
          "id": 68227,
          "bgColor": "none",
          "lottieUrl": "https://assets8.lottiefiles.com/private_files/lf30_agmathcb.json",
          "gifUrl": "https://assets8.lottiefiles.com/render/kqvt95k0.gif",
          "videoUrl": "https://assets8.lottiefiles.com/render/kqvt95k0.mp4",
          "imageUrl": "https://assets7.lottiefiles.com/render/kqvt95k0.png",
          "createdAt": "2021-07-09T03:14:51.000Z",
          "name": "ddocdoc_Bell",
          "createdBy": {
            "avatarUrl": "https://assets7.lottiefiles.com/avatars/300_5e5b56fb16079.jpg",
            "name": "ê¹€íƒœì˜"
          }
        }
      ]
    }
  }
}
"""
        )
        mockWebServer.enqueue(mockedResponse)
    }

    @Test
    fun testLoadData() = runBlocking {
        // Arrange
        assert(viewModel.recent.firstOrNull()?.isEmpty() != false)

        // Act
        viewModel.loadData()

        // Assert
        assert(viewModel.recent.first().isNotEmpty())
    }
}
