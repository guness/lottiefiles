package com.guness.lottie

import android.content.Context
import androidx.annotation.CallSuper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.guness.lottie.data.repo.AnimationRepository
import com.guness.lottie.data.repo.UserRepository
import com.guness.lottie.data.webservice.ApiWebservice
import com.guness.lottie.di.ApiModule
import com.guness.lottie.di.DatabaseModule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by guness on 17.01.2022 23:56
 */
@RunWith(AndroidJUnit4::class)
abstract class LottieUnitTest {

    protected val context: Context by lazy { ApplicationProvider.getApplicationContext() }
    protected val json by lazy { Json(builderAction = ApiModule.jsonConfig) }

    protected val database by lazy { DatabaseModule.providesDatabase(context) }

    protected val userRepository by lazy { UserRepository(database.userDao()) }
    protected val animationRepository by lazy { AnimationRepository(webservice, database.animationDao()) }

    protected val mockWebServer by lazy { MockWebServer() }


    private val webservice: ApiWebservice by lazy {

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiWebservice::class.java)
    }

    @Before
    @CallSuper
    open fun setUp() = Unit

    @After
    @CallSuper
    open fun tearDown() {
        mockWebServer.shutdown()
    }
}
