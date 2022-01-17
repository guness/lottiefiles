package com.guness.lottie.data.db

import androidx.room.TypeConverter
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.di.ApiModule
import kotlinx.serialization.json.Json
import java.time.Duration
import java.time.Instant

object LottieTypeConverters {

    private val json = Json(builderAction = ApiModule.jsonConfig)

    @TypeConverter
    fun toInstant(value: Long?): Instant? {
        return value?.let(Instant::ofEpochMilli)
    }

    @TypeConverter
    fun fromInstant(value: Instant?): Long? {
        return value?.toEpochMilli()
    }

    @TypeConverter
    fun toDuration(milliseconds: Long?): Duration? {
        return milliseconds?.let(Duration::ofMillis)
    }

    @TypeConverter
    fun fromDuration(duration: Duration?): Long? {
        return duration?.toMillis()
    }

    @TypeConverter
    fun toAnimator(value: String?): Animator? {
        return value?.let { json.decodeFromString(Animator.serializer(), it) }
    }

    @TypeConverter
    fun fromAnimator(value: Animator?): String? {
        return value?.let { json.encodeToString(Animator.serializer(), it) }
    }

    @TypeConverter
    fun toSet(value: String?): Set<Long>? {
        return value?.split(",")?.mapNotNull { it.toLongOrNull() }?.toSet()
    }

    @TypeConverter
    fun fromSet(value: Set<Long>?): String? {
        return value?.joinToString(",")
    }
}
