package com.guness.lottie.data.repo

import android.content.Context
import com.guness.lottie.data.dto.Article
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(@ApplicationContext val context: Context) {

    val articles = emptyFlow<List<Article>>()

    suspend fun putArticle(article: Article) = withContext(Dispatchers.IO) {

    }
}
