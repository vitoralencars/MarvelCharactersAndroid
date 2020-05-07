package com.vitor.myapplication.di

import com.vitor.myapplication.repository.FavoriteCharactersRepository
import com.vitor.myapplication.repository.ServiceCharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ServiceCharactersRepository(get()) }
    single { FavoriteCharactersRepository(get()) }
}