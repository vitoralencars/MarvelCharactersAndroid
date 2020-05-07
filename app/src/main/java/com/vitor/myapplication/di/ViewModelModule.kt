package com.vitor.myapplication.di

import com.vitor.myapplication.viewmodel.FavoriteCharactersViewModel
import com.vitor.myapplication.viewmodel.ServiceCharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ServiceCharactersViewModel(get()) }
    viewModel { FavoriteCharactersViewModel(get()) }
}