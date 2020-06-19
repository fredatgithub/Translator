package com.example.translator.model.response

data class TranslationResult(
    var code: Int,
    var lang: String,
    var text: List<String>
)
