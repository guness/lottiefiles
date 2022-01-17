package com.guness.lottie.data.db

import androidx.room.*
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_ANIMATION
import com.guness.lottie.data.dto.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(animation: DetailedAnimation): Long

    @Insert(entity = DetailedAnimation::class)
    suspend fun update(animation: RecentAnimation)

    @Insert(entity = DetailedAnimation::class)
    suspend fun update(animation: PopularAnimation)

    @Insert(entity = DetailedAnimation::class)
    suspend fun update(animation: FeaturedAnimation)

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM $TABLE_ANIMATION WHERE animationId = :id LIMIT 1")
    suspend fun get(id: Long): Animation?

    @Query("SELECT * FROM $TABLE_ANIMATION")
    fun observeAll(): Flow<List<Animation>>

    @Query("SELECT * FROM $TABLE_ANIMATION WHERE recent=1")
    fun observeAllRecent(): Flow<List<Animation>>

    @Query("SELECT * FROM $TABLE_ANIMATION WHERE popular=1")
    fun observeAllPopular(): Flow<List<Animation>>

    @Query("SELECT * FROM $TABLE_ANIMATION WHERE featured=1")
    fun observeAllFeatured(): Flow<List<Animation>>

    @Query("DELETE FROM $TABLE_ANIMATION")
    suspend fun clear()

    @Transaction
    suspend fun addAllRecent(list: List<RecentAnimation>) {
        list.forEach {
            if (add(DetailedAnimation(it.animation, recent = it.recent)) == -1L) {
                update(it)
            }
        }
    }

    @Transaction
    suspend fun addAllPopular(list: List<PopularAnimation>) {
        list.forEach {
            if (add(DetailedAnimation(it.animation, popular = it.popular)) == -1L) {
                update(it)
            }
        }
    }

    @Transaction
    suspend fun addAllFeatured(list: List<FeaturedAnimation>) {
        list.forEach {
            if (add(DetailedAnimation(it.animation, featured = it.featured)) == -1L) {
                update(it)
            }
        }
    }
}
