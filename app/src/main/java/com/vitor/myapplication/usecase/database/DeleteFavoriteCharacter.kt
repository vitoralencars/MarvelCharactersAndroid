package com.vitor.myapplication.usecase.database

import com.vitor.myapplication.model.Character
import com.vitor.myapplication.repository.FavoriteCharactersRepository

class DeleteFavoriteCharacter(private val repository: FavoriteCharactersRepository) {
    suspend operator fun invoke(character: Character) = repository.deleteFavoriteCharacter(character)
}