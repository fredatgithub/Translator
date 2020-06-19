package com.example.translator

import android.app.Application
import com.example.translator.di.apiModule
import com.example.translator.di.repositoryModule
import com.example.translator.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(apiModule, repositoryModule, viewModelModule))
        }
    }
}