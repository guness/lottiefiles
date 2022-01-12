package com.guness.lottie.data.db

import androidx.room.TypeConverter
import java.time.Duration
import java.time.Instant

class LottieTypeConverters {

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
}
