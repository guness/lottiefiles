package com.guness.lottie.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.guness.lottie.BuildConfig
import com.guness.lottie.data.db.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): LottieDatabase {
        return Room.databaseBuilder(context, LottieDatabase::class.java, LottieDatabase.DB_NAME)
            .also { if (BuildConfig.DEBUG) it.fallbackToDestructiveMigrationOnDowngrade() }
            .addMigrations(*migrations)
            .build()
    }

    @Provides
    fun providesAnimationDao(db: LottieDatabase): AnimationDao {
        return db.animationDao()
    }

    @Provides
    fun providesAnimatorDao(db: LottieDatabase): AnimatorDao {
        return db.animatorDao()
    }

    @Provides
    fun providesBlogDao(db: LottieDatabase): BlogDao {
        return db.blogDao()
    }

    @Provides
    fun providesUserDao(db: LottieDatabase): UserDao {
        return db.userDao()
    }

    private val migrations by lazy {
        emptyArray<Migration>()
    }
}
