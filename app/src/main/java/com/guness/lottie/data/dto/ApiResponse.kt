package com.guness.lottie.data.dto

import kotlinx.serialization.Serializable

/**
 * Created by guness on 12.01.2022 14:10
 */
@Serializable
class ApiResponse<T>(val error: ApiError? = null, val data: Map<String, Results<T>>? = null)

@Serializable
data class ApiError(val code: Int, val message: String)

@Serializable
data class Results<T>(
    val results: List<T>,
    val currentPage: Int? = null,
    val perPage: Int? = null,
    val totalPages: Int? = null,
    val from: Int? = null,
    val to: Int? = null,
    val total: Int? = null
)
