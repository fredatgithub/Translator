package com.example.translator.view.fragment.select

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translator.model.data.Language
import com.example.translator.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class SelectLanguageViewModel(private val repository: Repository) : ViewModel() {

    val error = MutableLiveData<String>()
    val languages = MutableLiveData<List<Language>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        error.postValue(throwable.message)
    }

    init {
        viewModelScope.launch(exceptionHandler) {
            repository.getLanguages().apply {
                val langs = mutableListOf<Language>()
                this.body()?.let {
                    it.langs?.map {
                        langs.add(Language(it.key, it.value))
                    }
                }
                languages.postValue(langs)
            }
        }
    }
}