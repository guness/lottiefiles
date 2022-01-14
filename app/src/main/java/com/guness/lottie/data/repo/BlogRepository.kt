package com.guness.lottie.data.repo

import com.guness.lottie.data.db.BlogDao
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.utils.UnexpectedApiError
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by guness on 13.01.2022 12:26
 */
@Singleton
class BlogRepository @Inject constructor(private val webservice: ApiWebservice, private val dao: BlogDao) {

    val blogs = dao.observeAll()

    suspend fun updateBlogs() {
        val blogs = webservice.getBlogs().data?.values?.first()?.results ?: throw UnexpectedApiError()
        dao.setAll(blogs)
    }
}
