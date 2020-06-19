package com.example.translator.di

import com.example.translator.model.ApiClient
import com.example.translator.repository.Repository
import com.example.translator.view.fragment.select.SelectLanguageViewModel
import com.example.translator.view.fragment.translation.TranslationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { ApiClient.create(get()) }
}

val repositoryModule = module {
    single { Repository(get()) }
}

val viewModelModule = module(override = true) {
    viewModel {
        TranslationViewModel(get())
    }
    viewModel {
        SelectLanguageViewModel(get())
    }
}