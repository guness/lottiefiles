package com.guness.lottie.data.repo

import com.guness.lottie.data.db.AnimatorDao
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.utils.UnexpectedApiError
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by guness on 13.01.2022 12:26
 */
@Singleton
class AnimatorRepository @Inject constructor(private val webservice: ApiWebservice, private val dao: AnimatorDao) {

    val animators = dao.observeAll()

    suspend fun updateAnimators() {
        val animators = webservice.getAnimators().data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.setAll(animators)
    }
}
