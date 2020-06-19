package com.example.translator.view.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translator.model.response.TranslationResult
import com.example.translator.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TranslationViewModel(private val repository: Repository) : ViewModel() {

    val isProgressVisible = MutableLiveData<Boolean>().apply { value = false }
    val error = MutableLiveData<String>()
    val translationResult = MutableLiveData<TranslationResult>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue(throwable.message)
    }

    fun translate(text: String, dir: String?) {
        isProgressVisible.value = true

        viewModelScope.launch(exceptionHandler) {
            repository.translate(text, dir ?: "").apply {
                translationResult.postValue(body())
                error.postValue(errorBody().toString())
            }
            isProgressVisible.postValue(false)
        }
    }
}