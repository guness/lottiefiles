package com.guness.lottie.di

import com.guness.lottie.data.webservice.ApiWebservice
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/lottiefiles-test.appspot.com/o/"

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(BASE_URL)
            .client(okHttpClient)
            //.addCallAdapterFactory(errorAdapter)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))

        return builder.build()
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
    }

    @Provides
    fun providesApiWebservice(retrofit: Retrofit): ApiWebservice {
        return retrofit.create(ApiWebservice::class.java)
    }

    @Provides
    fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            serializersModule = SerializersModule { }
            coerceInputValues = true
        }
    }
}
