package com.guness.lottie.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_ANIMATOR
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_BLOG
import com.guness.lottie.data.dto.Animator
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
}
