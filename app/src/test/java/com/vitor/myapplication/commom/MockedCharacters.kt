package com.vitor.myapplication.commom

import com.vitor.myapplication.model.*

object MockedCharacters {

    private val character1 = Character(
        1L,
        "Name 1",
        "Description 1",
        Thumbnail("path1", "ext1"),
        Participations(1),
        Participations(1),
        Participations(1),
        Participations(1)
    )

    private val character2 = Character(
        2L,
        "Name 2",
        "Description 2",
        Thumbnail("path2", "ext2"),
        Participations(2),
        Participations(2),
        Participations(2),
        Participations(2)
    )

    fun getFetchCharacteresRequest(): FetchCharactersRequest{
        val fetchCharactersResquest = FetchCharactersRequest(null, 0, 10)
        fetchCharactersResquest.apply {
            apikey = ""
            timestamp = 1L
            hash = ""
        }

        return fetchCharactersResquest
    }

    fun getFetchCharacteresRequestByName(): FetchCharactersRequest{
        val fetchCharactersResquest = FetchCharactersRequest("", 0, 10)
        fetchCharactersResquest.apply {
            apikey = ""
            timestamp = 1L
            hash = ""
        }

        return fetchCharactersResquest
    }

    val charactersList = listOf(
        character1,
        character2
    )

    val fetchCharactersResponse = FetchCharactersResponse(
        Data(1000, 20, charactersList)
    )

}