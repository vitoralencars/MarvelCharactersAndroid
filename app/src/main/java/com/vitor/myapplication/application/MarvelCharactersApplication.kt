package com.vitor.myapplication.application

import android.app.Application
import com.vitor.myapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelCharactersApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }

    private fun setUpKoin(){
        startKoin {
            androidContext(this@MarvelCharactersApplication)
            modules(listOf(
                serviceModule,
                repositoryModule,
                useCaseModule,
                databaseModule,
                viewModelModule
            ))
        }
    }

}