package com.guness.lottie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.Blog
import com.guness.lottie.data.dto.DetailedAnimation
import com.guness.lottie.data.dto.User

@Database(entities = [DetailedAnimation::class, Animator::class, Blog::class, User::class], version = LottieDatabase.DB_VERSION)
@TypeConverters(LottieTypeConverters::class)
abstract class LottieDatabase : RoomDatabase() {

    abstract fun animationDao(): AnimationDao

    abstract fun animatorDao(): AnimatorDao

    abstract fun blogDao(): BlogDao

    abstract fun userDao(): UserDao

    companion object {

        const val DB_VERSION = 1
        const val DB_NAME = "LottieFilesDatabase.db"

        const val TABLE_ANIMATION = "Animation"
        const val TABLE_ANIMATOR = "Animator"
        const val TABLE_BLOG = "Blog"
        const val TABLE_USER = "User"
    }
}
