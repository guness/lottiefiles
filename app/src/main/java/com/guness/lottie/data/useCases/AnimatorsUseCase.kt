package com.guness.lottie.data.useCases

import com.guness.lottie.data.db.ArticleDao
import com.guness.lottie.data.db.LottieDatabase
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.utils.UnexpectedApiError
import javax.inject.Inject

/**
 * Created by guness on 12.01.2022 15:05
 */
class AnimatorsUseCase @Inject constructor(
    private val webservice: ApiWebservice,
    private val dao: ArticleDao
) : UseCase<List<Animator>>() {

    override suspend fun update() {
        val animators = webservice.getAnimators().data?.values?.first()?.results ?: throw UnexpectedApiError()
        _data.emit(animators)
    }
}
