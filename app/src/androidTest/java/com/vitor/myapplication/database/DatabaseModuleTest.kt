package com.vitor.myapplication.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vitor.myapplication.db.FavoriteCharactersDatabase
import org.koin.dsl.module

val databaseModuleTest = module {
    single {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoriteCharactersDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    single { get<FavoriteCharactersDatabase>().favoriteCharactersDao() }
}