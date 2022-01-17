package com.guness.lottie.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guness.lottie.data.db.LottieDatabase

/**
 * Created by guness on 17.01.2022 10:56
 */
@Entity(tableName = LottieDatabase.TABLE_USER)
data class User(
    @PrimaryKey val id: Long = 0,
    val name: String,
    val bookmarks: Set<Long> = emptySet()
)
