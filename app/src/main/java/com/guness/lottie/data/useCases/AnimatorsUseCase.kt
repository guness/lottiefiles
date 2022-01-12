package com.guness.lottie.data.useCases

import com.guness.lottie.data.db.AnimatorDao
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.utils.UnexpectedApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by guness on 12.01.2022 15:05
 */
class AnimatorsUseCase @Inject constructor(
    private val webservice: ApiWebservice,
    private val dao: AnimatorDao
) : UseCase<List<Animator>>() {

    override suspend fun update() = withContext(Dispatchers.IO) {
        val animators = webservice.getAnimators().data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.addAll(animators)
    }
}
