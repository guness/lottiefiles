package com.guness.lottie.data.repo

import com.guness.lottie.data.db.AnimationDao
import com.guness.lottie.data.dto.FeaturedAnimation
import com.guness.lottie.data.dto.PopularAnimation
import com.guness.lottie.data.dto.RecentAnimation
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.utils.UnexpectedApiError
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by guness on 13.01.2022 12:26
 */
@Singleton
class AnimationRepository @Inject constructor(private val webservice: ApiWebservice, private val dao: AnimationDao) {

    val featured = dao.observeAllFeatured()
    val recent = dao.observeAllRecent()
    val popular = dao.observeAllPopular()

    suspend fun animation(id: Long) = dao.get(id)

    suspend fun updateFeatured() {
        val response = webservice.getFeaturedAnimations()
        val animations = response.data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.addAllFeatured(animations.map(::FeaturedAnimation))
    }

    suspend fun updateRecent() {
        val animations = webservice.getRecentAnimations().data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.addAllRecent(animations.map(::RecentAnimation))
    }

    suspend fun updatePopular() {
        val animations = webservice.getPopularAnimations().data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.addAllPopular(animations.map(::PopularAnimation))
    }
}
