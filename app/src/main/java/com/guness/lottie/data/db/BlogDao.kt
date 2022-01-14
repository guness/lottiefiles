package com.guness.lottie.data.db

import androidx.room.*
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_BLOG
import com.guness.lottie.data.dto.Blog
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(blog: Blog): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<Blog>)

    @Query("SELECT * FROM $TABLE_BLOG")
    fun observeAll(): Flow<List<Blog>>

    @Query("DELETE FROM $TABLE_BLOG")
    suspend fun clear()

    @Transaction
    suspend fun setAll(list: List<Blog>) {
        clear()
        addAll(list)
    }
}
