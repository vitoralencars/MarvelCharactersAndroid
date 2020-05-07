package com.vitor.myapplication.di

import com.vitor.myapplication.usecase.database.*
import com.vitor.myapplication.usecase.service.ServiceCharactersUseCases
import com.vitor.myapplication.usecase.service.FetchServiceCharacters
import org.koin.dsl.module

val useCaseModule = module {
    single { ServiceCharactersUseCases(
        FetchServiceCharacters(get())
    ) }

    single { FavoriteCharactersUseCases(
        FetchFavoriteCharacters(get()),
        InsertFavoriteCharacter(get()),
        DeleteFavoriteCharacter(get())
    ) }
}