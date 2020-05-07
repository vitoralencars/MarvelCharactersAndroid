package com.vitor.myapplication.repository

import com.vitor.myapplication.model.FetchCharactersRequest
import com.vitor.myapplication.service.route.CharactersRoute

class ServiceCharactersRepository(private val route: CharactersRoute) {

    suspend fun fetchCharacters(request: FetchCharactersRequest) =
        try {
            route.fetchCharacters(
                request.name,
                request.offset,
                request.apikey,
                request.timestamp,
                request.hash,
                request.limit
            )
        }catch (e: Exception){
            null
        }
}