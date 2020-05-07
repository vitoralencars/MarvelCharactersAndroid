package com.vitor.myapplication.db.datasource

import com.vitor.myapplication.db.dao.FavoriteCharactersDao
import com.vitor.myapplication.db.entity.CharacterEntity
import com.vitor.myapplication.model.Character

class FavoriteCharactersDataSource(private val dao: FavoriteCharactersDao) {

    suspend fun fetchFavoriteCharacters() = dao.fetchFavoriteCharacters().map { it.toCharacter() }

    suspend fun insertFavoriteCharacter(character: Character) = dao.insertCharacter(
        CharacterEntity.fromCharacter(character)
    )

    suspend fun deleteFavoriteCharacter(character: Character) = dao.deleteCharacter(
        CharacterEntity.fromCharacter(character)
    )
}