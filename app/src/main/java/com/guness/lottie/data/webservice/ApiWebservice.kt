package com.guness.lottie.data.webservice

import com.guness.lottie.data.dto.Animation
import com.guness.lottie.data.dto.Animator
import com.guness.lottie.data.dto.ApiResponse
import com.guness.lottie.data.dto.Blog
import retrofit2.http.GET

/**
 * Created by guness on 12.01.2022 14:05
 */
interface ApiWebservice {

    @GET("featuredAnimations.json?alt=media&token=f6e406f5-befb-40ab-a9b0-bb0a773b09fd")
    suspend fun getFeaturedAnimations(): ApiResponse<Animation>

    @GET("popularAnimations.json?alt=media&token=a32b4948-d278-4923-880e-8fb57741c190")
    suspend fun getPopularAnimations(): ApiResponse<Animation>

    @GET("recentAnimations.json?alt=media&token=f5acfd96-384a-4552-a0b5-399675a03826")
    suspend fun getRecentAnimations(): ApiResponse<Animation>

    @GET("featuredAnimators.json?alt=media&token=5b3e8205-b317-45c4-a5ce-36c9dc57911d")
    suspend fun getAnimators(): ApiResponse<Animator>

    @GET("blogs.json?alt=media&token=c6bf2153-7a69-4a47-9e3a-6f7500d8f523")
    suspend fun getBlogs(): ApiResponse<Blog>
}