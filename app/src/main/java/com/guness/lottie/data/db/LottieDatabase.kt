package com.guness.lottie.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guness.lottie.data.dto.Article

@Database(entities = [Article::class], version = LottieDatabase.DB_VERSION)
@TypeConverters(LottieTypeConverters::class)
abstract class LottieDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {

        const val DB_VERSION = 1
        const val DB_NAME = "LottieDatabase.db"

        const val TABLE_ARTICLES = "Article"
    }
}
