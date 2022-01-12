package com.guness.lottie.data.useCases

import com.guness.lottie.data.db.AnimationDao
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.PopularAnimation
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.utils.UnexpectedApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by guness on 12.01.2022 15:05
 */
class PopularUseCase @Inject constructor(
    private val webservice: ApiWebservice,
    private val dao: AnimationDao
) : UseCase<List<Animator>>() {

    override suspend fun update() = withContext(Dispatchers.IO) {
        val animators = webservice.getPopularAnimations().data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.addAllPopular(animators.map(::PopularAnimation))
    }
}
