package com.vitor.myapplication.repository

import com.vitor.myapplication.db.datasource.FavoriteCharactersDataSource
import com.vitor.myapplication.model.Character

class FavoriteCharactersRepository(private val dataSource: FavoriteCharactersDataSource) {

    suspend fun fetchFavoriteCharacters() = dataSource.fetchFavoriteCharacters()

    suspend fun insertFavoriteCharacter(character: Character) = dataSource.insertFavoriteCharacter(character)

    suspend fun deleteFavoriteCharacter(character: Character) = dataSource.deleteFavoriteCharacter(character)

}