package com.example.translator.model

import android.content.Context
import com.example.translator.BuildConfig.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig.DEBUG
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ApiClient {

    fun create(context: Context): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(OfflineCacheInterceptor(context))
            .addInterceptor(loggingInterceptor)
            .cache(Cache(File(context.cacheDir, "ResponsesCache"), (10 * 1024 * 1024).toLong()))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}