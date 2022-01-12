package com.guness.lottie.data.dto

import kotlinx.serialization.Serializable

/**
 * Created by guness on 12.01.2022 14:14
 */
@Serializable
data class Animation(
    val id: Long,
    val name: String,
    val bgColor: String,
    val lottieUrl: String,
    val gifUrl: String,
    val videoUrl: String,
    val imageUrl: String,
    val createdAt: String,
    val createdBy: Animator
)
