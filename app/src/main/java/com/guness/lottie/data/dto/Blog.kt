package com.guness.lottie.data.dto

import kotlinx.serialization.Serializable

/**
 * Created by guness on 12.01.2022 14:37
 */
@Serializable
data class Blog(val title: String, val postedAt: String, val imageUrl: String)
