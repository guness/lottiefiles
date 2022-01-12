package com.guness.lottie.data.repo

import android.content.Context
import com.guness.lottie.data.db.ArticleDao
import com.guness.lottie.data.dto.Article
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(@ApplicationContext val context: Context, private val articleDao: ArticleDao) {

    val articles = articleDao.observeAll()

    suspend fun putArticle(article: Article) = withContext(Dispatchers.IO) {
        articleDao.add(article)
    }
}
