package com.example.translator.model

import com.example.translator.model.response.LanguageDetectionResult
import com.example.translator.model.response.TranslationDirs
import com.example.translator.model.response.TranslationResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("getLangs")
    suspend fun getLanguages(
        @Query("key") key: String,
        @Query("ui") ui: String
    ): Response<TranslationDirs>

    @GET("detect")
    suspend fun detectLanguage(
        @Query("key") key: String,
        @Query("text") text: String
    ): Response<LanguageDetectionResult>

    @GET("translate")
    suspend fun translate(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("lang") lang: String
    ): Response<TranslationResult>
}