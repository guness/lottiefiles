package com.guness.lottie.data.db

import androidx.room.*
import com.guness.lottie.data.db.LottieDatabase.Companion.TABLE_USER
import com.guness.lottie.data.dto.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(user: User)

    @Query("SELECT * FROM $TABLE_USER LIMIT 1")
    suspend fun get(): User?

    @Query("SELECT * FROM $TABLE_USER LIMIT 1")
    fun user(): Flow<User?>

    @Query("UPDATE $TABLE_USER SET name = :name")
    suspend fun setName(name: String)

    @Transaction
    suspend fun setBookmark(animationId: Long) {
        get()?.let { user ->
            set(user.copy(bookmarks = user.bookmarks + animationId))
        }
    }

    @Transaction
    suspend fun removeBookmark(animationId: Long) {
        get()?.let { user ->
            set(user.copy(bookmarks = user.bookmarks - animationId))
        }
    }

    @Query("DELETE FROM $TABLE_USER")
    suspend fun clear()
}
