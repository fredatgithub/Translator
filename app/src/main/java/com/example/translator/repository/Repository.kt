package com.example.translator.repository

import com.example.translator.BuildConfig.API_KEY
import com.example.translator.model.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun translate(text: String, lang: String) = apiService.translate(API_KEY, text, lang)
}