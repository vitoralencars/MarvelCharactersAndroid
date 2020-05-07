package com.vitor.myapplication.usecase.database

import com.vitor.myapplication.repository.FavoriteCharactersRepository

class FetchFavoriteCharacters(private val repository: FavoriteCharactersRepository) {
    suspend operator fun invoke() = repository.fetchFavoriteCharacters()
}
