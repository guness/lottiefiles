package com.guness.lottie.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guness.lottie.data.db.LottieDatabase

@Entity(tableName = LottieDatabase.TABLE_ARTICLES)
data class Article(@PrimaryKey val url: String, val text: String, val image: String)