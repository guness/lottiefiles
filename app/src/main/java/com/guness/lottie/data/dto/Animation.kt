package com.guness.lottie.data.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.guness.lottie.data.db.LottieDatabase
import com.guness.lottie.data.db.LottieTypeConverters
import com.guness.lottie.utils.serializers.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

/**
 * Created by guness on 12.01.2022 14:14
 */
@Serializable
data class Animation(
    val id: Long,
    val name: String,
    val bgColor: String,
    val lottieUrl: String,
    val gifUrl: String?,
    val videoUrl: String?,
    val imageUrl: String,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant,
    val createdBy: Animator
)

@Entity(tableName = LottieDatabase.TABLE_ANIMATION)
@TypeConverters(LottieTypeConverters::class)
data class DetailedAnimation(
    @Embedded
    val animation: Animation,
    @PrimaryKey
    val animationId: Long = animation.id,
    val recent: Boolean? = null,
    val popular: Boolean? = null,
    val featured: Boolean? = null
)

data class RecentAnimation(
    @Embedded
    val animation: Animation,
    val animationId: Long = animation.id,
    val recent: Boolean = true
)

data class PopularAnimation(
    @Embedded
    val animation: Animation,
    val animationId: Long = animation.id,
    val popular: Boolean = true
)


data class FeaturedAnimation(
    @Embedded
    val animation: Animation,
    val animationId: Long = animation.id,
    val featured: Boolean = true
)


val MockAnimation = Animation(
    id = 0,
    name = "Name One",
    bgColor = "#FFBBEE",
    lottieUrl = "https://assets9.lottiefiles.com/private_files/lf30_s1GCPP.json",
    gifUrl = null,
    videoUrl = null,
    imageUrl = "imageUrl",
    createdAt = Instant.now(),
    createdBy = Animator(id = 0, name = "name", avatarUrl = "avatarUrl")
)