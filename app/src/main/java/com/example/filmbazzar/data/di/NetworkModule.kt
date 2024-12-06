package com.example.filmbazzar.data.di

import androidx.compose.ui.util.trace
import com.example.filmbazzar.BuildConfig
import com.example.filmbazzar.data.API_KEY
import com.example.filmbazzar.data.BASE_URL
import com.example.filmbazzar.data.network.TMDBService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun getAuthorizationTokenInterceptor(): Interceptor {
        return Interceptor {
            val actualRequest =
                it.request().newBuilder().addHeader("authorization", "Bearer $API_KEY").build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizationTokenInterceptor: Interceptor
    ): Call.Factory = trace("TMDBOkHttpClient") {
        OkHttpClient.Builder()
            .connectTimeout(16, TimeUnit.SECONDS)
            .readTimeout(16, TimeUnit.SECONDS)
            .writeTimeout(16, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authorizationTokenInterceptor
            ).build()
    }

    @Provides
    @Singleton
    fun providesTMDBService(
        okhttpCallFactory: dagger.Lazy<Call.Factory>, networkJson: Json
    ): TMDBService {
        return trace("RetrofitTMDBNetwork") {
            Retrofit.Builder().baseUrl(BASE_URL)
                .callFactory { okhttpCallFactory.get().newCall(it) }.
                addConverterFactory(
                    networkJson.asConverterFactory("application/json".toMediaType()),
                ).build().create(TMDBService::class.java)
        }
    }
}