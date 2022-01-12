package com.guness.lottie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_ARTICLES
import com.guness.lottie.data.dto.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(articles: Article)

    @Query("SELECT * FROM $TABLE_ARTICLES")
    fun observeAll(): Flow<List<Article>>

    @Query("DELETE FROM Article")
    suspend fun clear()
}
