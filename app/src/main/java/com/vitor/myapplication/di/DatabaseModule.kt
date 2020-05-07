package com.vitor.myapplication.di

import androidx.room.Room
import com.vitor.myapplication.db.FavoriteCharactersDatabase
import com.vitor.myapplication.db.datasource.FavoriteCharactersDataSource
import com.vitor.myapplication.util.constant.DatabaseConstants
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            FavoriteCharactersDatabase::class.java, DatabaseConstants.CHARACTER_TABLE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single { get<FavoriteCharactersDatabase>().favoriteCharactersDao() }

    single { FavoriteCharactersDataSource(get()) }
}