package com.vitor.myapplication.util.constant

object DatabaseConstants {
    const val DATABASE_NAME = "MarvelCharactersAppDB"

    const val CHARACTER_TABLE_NAME = "Character"

    const val SELECT_FAVORITES_CHARACTERS_QUERY = "SELECT * FROM $CHARACTER_TABLE_NAME"
}