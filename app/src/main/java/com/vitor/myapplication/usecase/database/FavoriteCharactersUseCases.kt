package com.vitor.myapplication.usecase.database

data class FavoriteCharactersUseCases (
    val fetchFavoriteCharacters: FetchFavoriteCharacters,
    val insertFavoriteCharacter: InsertFavoriteCharacter,
    val deleteFavoriteCharacter: DeleteFavoriteCharacter
)