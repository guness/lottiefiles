package com.guness.lottie.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guness.lottie.data.db.LottieDatabase
import kotlinx.serialization.Serializable

/**
 * Created by guness on 12.01.2022 14:16
 */
@Serializable
@Entity(tableName = LottieDatabase.TABLE_ANIMATOR)
data class Animator(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val avatarUrl: String
)
