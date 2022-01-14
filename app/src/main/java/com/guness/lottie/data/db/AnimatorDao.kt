package com.guness.lottie.data.db

import androidx.room.*
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_ANIMATOR
import com.guness.lottie.data.dto.Animator
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimatorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(animator: Animator): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<Animator>)

    @Query("SELECT * FROM $TABLE_ANIMATOR")
    fun observeAll(): Flow<List<Animator>>

    @Query("DELETE FROM $TABLE_ANIMATOR")
    suspend fun clear()

    @Transaction
    suspend fun setAll(list: List<Animator>) {
        clear()
        addAll(list)
    }
}
